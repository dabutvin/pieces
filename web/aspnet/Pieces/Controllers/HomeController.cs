using Pieces.Data;
using Pieces.Data.Models;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace Pieces.Controllers
{
    public class HomeController : Controller
    {
        private PiecesDbContext dbContext;

        public HomeController()
        {
            this.dbContext = new PiecesDbContext();
        }

        public async Task<ActionResult> Index()
        {

            //var artists = this.dbContext.Artists;

            //var danbut = await artists.FirstOrDefaultAsync(x => x.Username == "Dan But");

            //danbut.Pieces.Add(new Piece
            //{
            //    Title = "title uno",
            //    Medium = "medium uno",
            //    Description = "Inspired by science fictional depictions of arid worlds and the cultural practices that develop around water scarcity.",
            //    MainImageUrl = "http://lorempixel.com/250/250/",
            //    ImageUrl2 = "http://lorempixel.com/249/249/"
            //});

            //await this.dbContext.SaveChangesAsync();

            return Content("WEBSITE!!!");
        }
    }
}