using System.Web;

namespace Pieces.Json.Models
{
    public abstract class BaseModel
    {
        protected string BaseUrl
        {
            get
            {
                var url = HttpContext.Current.Request.Url;
                return $"{url.Scheme}://{url.Authority}";
            }
        }
    }
}