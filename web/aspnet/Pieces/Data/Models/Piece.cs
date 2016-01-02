using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Pieces.Data.Models
{
    public class Piece
    {
        public Piece()
        {
            this.LikedUsers = new HashSet<User>();
            this.DislikedUsers = new HashSet<User>();
        }

        [Key]
        public int PieceId { get; set; }
        public string Title { get; set; }
        public Artist Artist { get; set; }
        public string Medium { get; set; }
        public string Description { get; set; }

        public string MainImageUrl { get; set; }
        public string ImageUrl2 { get; set; }
        public string ImageUrl3 { get; set; }
        
        public virtual ICollection<User> LikedUsers { get; set; }
        public virtual ICollection<User> DislikedUsers { get; set; }
    }
}