var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/api/data', function(req, res, next) {
	res.json({
		pieces: [
			{
				title: "title uno",
				artist: "artist uno",
				medium: "medium uno",
				src: "http://lorempixel.com/250/250/"
			},
			{
				title: "title dos",
				artist: "artist dos",
				medium: "medium dos",
				src: "http://lorempixel.com/249/249/"
			}
		] 
	});
});

module.exports = router;
