using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Pieces.Data.Models
{
    public class User
    {
        public User()
        {
            this.LikedPieces = new HashSet<Piece>();
            this.DislikedPieces = new HashSet<Piece>();
        }

        [Key]
        public int UserId { get; set; }
        public string Username { get; set; }

        public virtual ICollection<Piece> LikedPieces { get; set; }
        public virtual ICollection<Piece> DislikedPieces { get; set; }
    }
}