using System.Web;

namespace Pieces.Json.Models
{
    public class Artist : BaseModel
    {
        public int id { get; set; }
        public string username { get; set; }
        public Piece[] pieces { get; set; }

        public string url
        {
            get
            {
                return $"{this.BaseUrl}/artists/{this.id}";
            }
        }
    }
}