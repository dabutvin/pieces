using Pieces.Data.Models;
using System.Data.Entity;

namespace Pieces.Data
{
    public class PiecesDbContext : DbContext
    {
        public PiecesDbContext():base("name=PiecesDbContext")
        {
        }

        public IDbSet<Artist> Artists
        {
            get
            {
                return this.Set<Artist>();
            }
        }

        public IDbSet<Piece> Pieces
        {
            get
            {
                return this.Set<Piece>();
            }
        }

        public IDbSet<User> Users
        {
            get
            {
                return this.Set<User>();
            }
        }
    }
}