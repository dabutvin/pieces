using Pieces.Data;
using Pieces.Data.Models;
using System.Data.Entity;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace Pieces.Controllers
{
    [RoutePrefix("users")]
    public class UsersController : Controller
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

            return Json(users, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        [Route("{id}")]
        public async Task<JsonResult> Get(int id)
        {
            var user = await this.dbContext.Users.FirstOrDefaultAsync(x => x.UserId == id);

            if (user == null)
            {
                throw new HttpException(404, "Not found.");
            }

            return Json(user, JsonRequestBehavior.AllowGet);
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