using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Pieces.Data.Models
{
    public class Artist
    {
        public Artist()
        {
            this.Pieces = new HashSet<Piece>();
        }

        [Key]
        public int ArtistId { get; set; }
        public string Username { get; set; }
        public ICollection<Piece> Pieces { get; set; }
    }
}