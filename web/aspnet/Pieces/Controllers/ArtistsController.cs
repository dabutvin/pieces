using Pieces.Data;
using Pieces.Data.Models;
using System.Data.Entity;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace Pieces.Controllers
{
    [RoutePrefix("artists")]
    public class ArtistsController : Controller
    {
        private PiecesDbContext dbContext;

        public ArtistsController()
        {
            this.dbContext = new PiecesDbContext();
        }

        [HttpGet]
        [Route("")]
        public async Task<JsonResult> Get()
        {
            var artists = await this.dbContext.Artists.ToArrayAsync();

            return Json(artists, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        [Route("{id}")]
        public async Task<JsonResult> Get(int id)
        {
            var artist = await this.dbContext.Artists.FirstOrDefaultAsync(x => x.ArtistId == id);

            if (artist == null)
            {
                throw new HttpException(404, "Not found.");
            }

            return Json(artist, JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        [Route("")]
        public async Task<JsonResult> Post(Artist artist)
        {
            var posted = this.dbContext.Artists.Add(artist);
            await this.dbContext.SaveChangesAsync();

            return Json(posted);
        }

        [HttpPut]
        [Route("{id}")]
        public async Task<JsonResult> Put(int id, Artist artist)
        {
            artist.ArtistId = id;
            dbContext.Artists.Attach(artist);
            var entry = dbContext.Entry(artist);

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