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
				id: 1,
				title: "title uno",
				artist: "artist uno",
				medium: "medium uno",
				src: "http://lorempixel.com/250/250/"
			},
			{
				id: 2,
				title: "title dos",
				artist: "artist dos",
				medium: "medium dos",
				src: "http://lorempixel.com/249/249/"
			}
		] 
	});
});

router.get('/api/data/:id', function (req, res, next) {
	res.json({
		id: req.params.id,
		title: "title uno",
		artist: "artist uno",
		medium: "medium uno",
		description: "Inspired by science fictional depictions of arid worlds and the cultural practices that develop around water scarcity.",
		srcset: [
			"http://lorempixel.com/250/250/",
			"http://lorempixel.com/251/251/",
			"http://lorempixel.com/252/252/",
		]
	});
});

module.exports = router;
