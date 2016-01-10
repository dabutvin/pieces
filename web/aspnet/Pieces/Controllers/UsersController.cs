using Pieces.Data;
using Pieces.Data.Models;
using Pieces.Json;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace Pieces.Controllers
{
    [RoutePrefix("users")]
    public class UsersController : BaseController
    {
        private PiecesDbContext dbContext;

        public UsersController()
        {
            this.dbContext = new PiecesDbContext();
        }

        [HttpGet]
        [Route("")]
        public async Task<JsonResult> Get()
        {
            var users = await this.dbContext.Users.ToArrayAsync();

            return Json(users.Select(x => x.ToUserJson()), JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        [Route("{id}")]
        public async Task<JsonResult> Get(int id)
        {
            var user = await this.dbContext
                .Users
                .Include(x => x.LikedPieces)
                .FirstOrDefaultAsync(x => x.UserId == id);

            if (user == null)
            {
                throw new HttpException(404, "Not found.");
            }

            return Json(user.ToUserJson(), JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        [Route("{id}/queue")]
        public async Task<JsonResult> GetQueue(int id)
        {
            var queue = await this.dbContext
                .Pieces
                .Where(piece => piece.DislikedUsers.Any(user => user.UserId == id) == false)
                .Where(piece => piece.LikedUsers.Any(user => user.UserId == id) == false)
                .Take(10)
                .Select(x => x.PieceId)
                .ToArrayAsync();

            return Json(queue, JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        [Route("")]
        public async Task<JsonResult> Post(User user)
        {
            var posted = this.dbContext.Users.Add(user);
            await this.dbContext.SaveChangesAsync();

            return Json(posted);
        }

        [HttpPut]
        [Route("{id}")]
        public async Task<JsonResult> Put(int id, User user)
        {
            user.UserId = id;
            dbContext.Users.Attach(user);
            var entry = dbContext.Entry(user);

            entry.Property(x => x.Username).IsModified = true;

            await this.dbContext.SaveChangesAsync();

            return Json("success");
        }

        protected override void Dispose(bool disposing)
        {
            this.dbContext.Dispose();
            base.Dispose(disposing);
        }
    }
}