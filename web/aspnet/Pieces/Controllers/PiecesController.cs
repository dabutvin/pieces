using Pieces.Data;
using Pieces.Data.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace Pieces.Controllers
{
    [RoutePrefix("pieces")]
    public class PiecesController : Controller
    {
        private PiecesDbContext dbContext;

        public PiecesController()
        {
            this.dbContext = new PiecesDbContext();
        }

        [HttpGet]
        [Route("")]
        public async Task<JsonResult> Get()
        {
            var pieces = await this.dbContext.Pieces.ToArrayAsync();

            return Json(pieces, JsonRequestBehavior.AllowGet);
        }

        [HttpGet]
        [Route("{id}")]
        public async Task<JsonResult> Get(int id)
        {
            var piece = await this.dbContext.Pieces.FirstOrDefaultAsync(x => x.PieceId == id);

            if(piece == null)
            {
                throw new HttpException(404, "Not found.");
            }

            return Json(piece, JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        [Route("")]
        public async Task<JsonResult> Post(Piece piece)
        {
            var posted = this.dbContext.Pieces.Add(piece);
            await this.dbContext.SaveChangesAsync();

            return Json(posted);
        }

        [HttpPut]
        [Route("{id}")]
        public async Task<JsonResult> Put(int id, Piece piece)
        {
            piece.PieceId = id;
            this.dbContext.Pieces.Attach(piece);
            var entry = this.dbContext.Entry(piece);

            entry.Property(x => x.Title).IsModified = true;
            entry.Property(x => x.Medium).IsModified = true;
            entry.Property(x => x.Description).IsModified = true;
            entry.Property(x => x.MainImageUrl).IsModified = true;
            entry.Property(x => x.ImageUrl2).IsModified = true;
            entry.Property(x => x.ImageUrl3).IsModified = true;

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