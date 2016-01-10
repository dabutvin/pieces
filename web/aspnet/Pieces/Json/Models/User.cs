namespace Pieces.Json.Models
{
    public class User : BaseModel
    {
        public int id { get; set; }
        public string username { get; set; }
        public Piece[] likedPieces { get; set; }

        public string url
        {
            get
            {
                return $"{this.BaseUrl}/users/{this.id}";
            }
        }
    }
}