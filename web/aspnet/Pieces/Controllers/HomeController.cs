using System.Web.Mvc;

namespace Pieces.Controllers
{
    public class HomeController : Controller
    {
        [Route("")]
        public ActionResult Index()
        {
            return Content("WEBSITE!!!");
        }
    }
}