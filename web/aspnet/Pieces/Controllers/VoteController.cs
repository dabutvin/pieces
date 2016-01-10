using Pieces.Data;
using System.Data.Entity;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace Pieces.Controllers
{
    [RoutePrefix("vote")]
    public class VoteController : BaseController
    {
        private PiecesDbContext dbContext;

        public VoteController()
        {
            this.dbContext = new PiecesDbContext();
        }

        [HttpPost]
        [Route("upvote")]
        public async Task<JsonResult> UpVote(int userId, int pieceId)
        {
            var piece = await this.dbContext.Pieces.FirstOrDefaultAsync(x => x.PieceId == pieceId);
            var user = await this.dbContext.Users.FirstOrDefaultAsync(x => x.UserId == userId);

            if (piece == null || user == null)
            {
                throw new HttpException(404, "Not found.");
            }

            piece.LikedUsers.Add(user);

            await this.dbContext.SaveChangesAsync();

            return Json("success");
        }

        [HttpPost]
        [Route("downvote")]
        public async Task<JsonResult> DownVote(int userId, int pieceId)
        {
            var piece = await this.dbContext.Pieces.FirstOrDefaultAsync(x => x.PieceId == pieceId);
            var user = await this.dbContext.Users.FirstOrDefaultAsync(x => x.UserId == userId);

            if (piece == null || user == null)
            {
                throw new HttpException(404, "Not found.");
            }

            piece.DislikedUsers.Add(user);

            await this.dbContext.SaveChangesAsync();

            return Json("success");
        }
    }
}