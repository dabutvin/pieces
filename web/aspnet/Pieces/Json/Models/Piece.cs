using System.Linq;

namespace Pieces.Json.Models
{
    public class Piece : BaseModel
    {
        public int id { get; set; }
        public string title { get; set; }
        public string medium { get; set; }
        public string description { get; set; }
        public string mainImageUrl { get; set; }
        public string imageUrl2 { get; set; }
        public string imageUrl3 { get; set; }

        public Artist artist { get; set; }

        public string url
        {
            get
            {
                return $"{this.BaseUrl}/pieces/{this.id}";
            }
        }
    }
}