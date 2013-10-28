
	$(document).ready(
			function() {

				$('#my-table').chartify('pie', {
					pieChartRotation : -1.256 / 2,
					chartType : 'p3',
					legendPosition : 'b'
				/* colors : [ "eeeeee", "ffc000" ] */
				});

				$('.product-view').chartify(
						'bar',
						{
							chartWidth : 700, // in pixels
							chartHeight : 700,
							colors : [ "6cc05c", "433840", "6cc05c", "ff710f",
									"ED1F27", "95a8ad", "0053aa" ],
							barWidth : 15,
							barSpacing : 6,
							legendPosition : 'b'
						});
				$('.most-wishlisted').chartify(
						'bar',
						{
							chartWidth : 700, // in pixels
							chartHeight : 700,
							legendPosition : 'b',
							colors : [ "ff710f", "433840", "6cc05c", "ff710f",
									"ED1F27", "95a8ad", "0053aa" ],
							barWidth : 15,
							barSpacing : 6,
						});

				$('.mostpopular-products').chartify(
						'bar',
						{
							chartWidth : 700, // in pixels
							chartHeight : 700,
							colors : [ "6cc05c", "433840", "6cc05c", "ff710f",
									"ED1F27", "95a8ad", "0053aa" ],
							barWidth : 15,
							barSpacing : 6,
							legendPosition : 'b'
						});

				$('.pie-chart').chartify('pie', {
					chartType : 'p3',
					unit : '%',
					isDistribution : true
				});

				$('.category-selling').chartify(
						'bar',
						{
							chartWidth : 700, // in pixels
							chartHeight : 700,
							colors : [ "ff710f", "433840", "6cc05c", "ff710f",
									"ED1F27", "95a8ad", "0053aa" ],
							barWidth : 15,
							barSpacing : 6,
							legendPosition : 'b'
						});

				$('.category-selling2').chartify('pie', {
					chartType : 'p3',
					unit : '%',
					isDistribution : true
				});

				$('.best-merchants').chartify(
						'bar',
						{
							chartWidth : 700, // in pixels
							chartHeight : 700,
							colors : [ "6cc05c", "433840", "6cc05c", "ff710f",
									"ED1F27", "95a8ad", "0053aa" ],
							barWidth : 15,
							barSpacing : 6,
							legendPosition : 'b'
						});

				$('.merchant-selling').chartify('pie', {
					chartType : 'p3',
					unit : '%',
					isDistribution : true
				});

				$('.best-products').chartify(
						'bar',
						{
							chartWidth : 700, // in pixels
							chartHeight : 700,
							colors : [ "ff710f", "433840", "6cc05c", "ff710f",
									"ED1F27", "95a8ad", "0053aa" ],
							barWidth : 15,
							barSpacing : 6,
							legendPosition : 'b'
						});

				$('.best-selling').chartify(
						'bar',
						{
							chartWidth : 700, // in pixels
							chartHeight : 700,
							colors : [ "ff710f", "433840", "6cc05c", "ff710f",
									"ED1F27", "95a8ad", "0053aa" ],
							barWidth : 15,
							barSpacing : 6,
							legendPosition : 'b'
						});

				$('.bar-chart').chartify(
						'bar',
						{
							chartWidth : 700, // in pixels
							chartHeight : 700,
							colors : [ "ff710f", "433840", "6cc05c", "ff710f",
									"ED1F27", "95a8ad", "0053aa" ],
							barWidth : 15,
							barSpacing : 6,
							isDistribution : true,

						});

				$('.revenue').chartify(
						'bar',
						{
							chartWidth : 1000, // in pixels
							chartHeight : 700,
							colors : [ "6cc05c", "433840", "6cc05c", "ff710f",
									"ED1F27", "95a8ad", "0053aa" ],
							barWidth : 15,
							barSpacing : 6,

						});

			});

