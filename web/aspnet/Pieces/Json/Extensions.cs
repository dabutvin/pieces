using System.Linq;

namespace Pieces.Json
{
    public static class Extensions
    {
        public static Models.Artist ToShallowArtistJson(this Data.Models.Artist artistData)
        {
            return new Models.Artist
            {
                id = artistData.ArtistId,
                username = artistData.Username,
            };
        }

        public static Models.Artist ToArtistJson(this Data.Models.Artist artistData)
        {
            return new Models.Artist
            {
                id = artistData.ArtistId,
                username = artistData.Username,

                pieces = artistData.Pieces.Select(ToShallowPieceJson).ToArray(),
            };
        }

        public static Models.Piece ToPieceJson(this Data.Models.Piece pieceData)
        {
            return new Models.Piece
            {
                id = pieceData.PieceId,
                title = pieceData.Title,
                description = pieceData.Description,
                medium = pieceData.Medium,
                mainImageUrl = pieceData.MainImageUrl,
                imageUrl2 = pieceData.ImageUrl2,
                imageUrl3 = pieceData.ImageUrl3,

                artist = pieceData.Artist.ToShallowArtistJson()
            };
        }

        public static Models.Piece ToShallowPieceJson(this Data.Models.Piece pieceData)
        {
            return new Models.Piece
            {
                id = pieceData.PieceId,
                title = pieceData.Title,
                description = pieceData.Description,
                medium = pieceData.Medium,
                mainImageUrl = pieceData.MainImageUrl,
                imageUrl2 = pieceData.ImageUrl2,
                imageUrl3 = pieceData.ImageUrl3,
            };
        }

        public static Models.User ToUserJson(this Data.Models.User userData)
        {
            return new Models.User
            {
                id = userData.UserId,
                username = userData.Username,
                likedPieces = userData.LikedPieces.Select(x => x.ToShallowPieceJson()).ToArray(),
            };
        }

        public static Models.User ToShallowUserJson(this Data.Models.User userData)
        {
            return new Models.User
            {
                id = userData.UserId,
                username = userData.Username,
            };
        }
    }
}