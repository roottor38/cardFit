<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Welcome CardFit</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700|Work+Sans:300,400,700"
	rel="stylesheet">
<link rel="stylesheet" href="fonts/icomoon/style.css">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">
<link rel="stylesheet" href="css/bootstrap-datepicker.css">
<link rel="stylesheet" href="css/animate.css">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/mediaelementplayer.min.css">



<link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">

<link rel="stylesheet" href="css/aos.css">

<link rel="stylesheet" href="css/style.css">

</head>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">

  	function selectOption(){
		var array = [];
		var checkboxes = document.querySelectorAll('input[type=checkbox]:checked');
		for (var i = 0; i < checkboxes.length; i++) {
		  array.push(checkboxes[i].value);
		}
		if(array.length==0){
			alert("키워드를 선택해주세요.");
		}else{
			var url = "http://localhost:8000/option?";
			url = url + array[0]+"="+array[0];
			for(var i = 1; i < array.length; i++){
				url = url + "&" + array[i] + "=" + array[i];
			}
			 axios.get(url)
		      .then(resData => {
		                 console.log(resData);
		      }).catch(error => {
						console.log("비정상 응답 ", error);
		      });
			 alert(url);
		     alert("작동완료");
		}
  	}
  

  </script>
<style type="text/css">
input[type=checkbox] {
	width: 20px;
	height: 20px;
}

#my1 {
	margin: 130px 0px 0px 0px;
}
</style>
<body>

	<div class="site-wrap">

		<div class="site-mobile-menu">
			<div class="site-mobile-menu-header">
				<div class="site-mobile-menu-close mt-3">
					<span class="icon-close2 js-menu-toggle"></span>
				</div>
			</div>
			<div class="site-mobile-menu-body"></div>
		</div>
		<!-- .site-mobile-menu -->


		<div class="site-navbar-wrap js-site-navbar bg-white">
			<div class="container">
				<div class="site-navbar bg-light">
					<div class="py-1">
						<div class="row align-items-center">
							<div class="col-2">
								<div class="mb-0 site-logo">
									<a href="index.html">Card<strong class="font-weight-bold">Fit</strong>
									</a>
								</div>
							</div>
							<div class="col-10">
								<nav class="site-navigation text-right" role="navigation">
									<div class="container">
										<div class="d-inline-block d-lg-none ml-md-0 mr-auto py-3">
											<a href="#"
												class="site-menu-toggle js-menu-toggle text-black"><span
												class="icon-menu h3"></span></a>
										</div>

										<ul class="site-menu js-clone-nav d-none d-lg-block">
											<li><a href="about.html">CardFit 소개</a></li>
											<li><a href="myBenefit.jsp">내 카드 혜택보기</a></li>
											<li class="has-children"><a>카드 추천받기</a>
												<ul class="dropdown arrow-top">
													<li><a href="/keyword">키워드로 추천받기</a></li>
													<li><a href="/option">옵션으로 추천받기</a></li>
												</ul></li>
											<li><a href="contact.html">문의하기</a></li>
										</ul>
									</div>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div style="height: 113px;"></div>

		<div class="site-blocks-cover overlay"
			style="background-image: url('images/hero_1.jpg');" data-aos="fade"
			data-stellar-background-ratio="0.5">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-12" data-aos="fade">
						<h2 style="color: white">옵션으로 추천받기</h2>
						<br>
						
						<form action="javascript:selectOption()" method="post" style="color:white">
							<div class="row mb-3">
								<div class="col-md-9">
									<div class="row">
										<table style="font-size: 25px;">
											<tr>
												<td>
												<input type="checkbox" id="movie" value="movie">영화<br>
												</td>
												<td>
												<input type="checkbox" id="mobile" value="mobile">통신사&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
												</td>
												<td>
												<input type="checkbox" id="transport" value="transport">대중교통 &nbsp;&nbsp;&nbsp; <br>
												</td>
												<td>
												<input type="checkbox" id="shopping" value="shopping">쇼핑 <br>
												</td>
											</tr>
											<tr>
												<td>
												<input type="checkbox" id="onlineShopping" value="onlineShopping">온라인쇼핑 &nbsp;&nbsp; <br>
												</td>
												<td>
												<input type="checkbox" id="eatOut" value="eatOut">외식<br>
												</td>
												<td>
												<input type="checkbox" id="Others" value="Others">기타<br>
												</td>
												<td>
												<input type="checkbox" id="vehicle2" value="Car">기타<br>
												</td>
											</tr>
											<tr>
												<td>
												<input type="checkbox" id="vehicl3e2" value="Car1">온라인쇼핑&nbsp;&nbsp; <br>
												</td>
												<td>
												<input type="checkbox" id="vehic4le2" value="Car2">외식<br>
												</td>
												<td>
												<input type="checkbox" id="vehic5le2" value="Car3">기타<br>
												</td>
												<td>
												<input type="checkbox" id="vehi6cle2" value="Car4">기타<br>
												</td>
											</tr>
											<tr>
												<td>
												<input type="checkbox" id="vehic7le2" value="Car5">온라인쇼핑&nbsp;&nbsp; <br>
												</td>
												<td>
												<input type="checkbox" id="veh4icle2" value="Car6">외식<br>
												</td>
												<td>
												<input type="checkbox" id="v1ehicle2" value="Car7">기타<br>
												</td>
												<td>
												<input type="checkbox" id="v2ehicle2" value="Car8">기타<br>
												</td>
											</tr>
										</table>
									</div>
								</div>
								
								<div id="my1" class="col-md-3">
								<input type="submit" class="btn btn-search btn-primary btn-block" value="Search">
								</div>
								
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>






		<div class="site-section" data-aos="fade">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-md-6 mb-5 mb-md-0">

						<div class="img-border">
							<a href="https://vimeo.com/28959265"
								class="popup-vimeo image-play"> <span class="icon-wrap">
									<span class="icon icon-play"></span>
							</span> <img src="images/hero_1.jpg" alt="Image"
								class="img-fluid rounded">
							</a>
						</div>

					</div>
					<div class="col-md-5 ml-auto">
						<div class="text-left mb-5 section-heading">
							<h2>Testimonies</h2>
						</div>

						<p class="mb-4 h5 font-italic lineheight1-5">&ldquo;Lorem
							ipsum dolor sit amet, consectetur adipisicing elit. Eaque, nisi
							Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit
							nobis magni eaque velit eum, id rem eveniet dolor possimus
							voluptas..&rdquo;</p>
						<p>
							&mdash; <strong class="text-black font-weight-bold">John
								Holmes</strong>, Marketing Strategist
						</p>
						<p>
							<a href="https://vimeo.com/28959265"
								class="popup-vimeo text-uppercase">Watch Video <span
								class="icon-arrow-right small"></span></a>
						</p>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section" data-aos="fade">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-md-6 mb-5 mb-md-0 order-md-2">

						<div class="img-border">
							<a href="https://vimeo.com/28959265"
								class="popup-vimeo image-play"> <span class="icon-wrap">
									<span class="icon icon-play"></span>
							</span> <img src="images/hero_2.jpg" alt="Image"
								class="img-fluid rounded">
							</a>
						</div>

					</div>
					<div class="col-md-5 ml-auto order-md-1">
						<div class="text-left mb-5 section-heading">
							<h2>Creative People</h2>
						</div>

						<p class="mb-4 h5 font-italic lineheight1-5">&ldquo;Lorem
							ipsum dolor sit amet, consectetur adipisicing elit. Eaque, nisi
							Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit
							nobis magni eaque velit eum, id rem eveniet dolor possimus
							voluptas..&rdquo;</p>
						<p>
							&mdash; <strong class="text-black font-weight-bold">John
								Holmes</strong>, Marketing Strategist
						</p>
						<p>
							<a href="https://vimeo.com/28959265"
								class="popup-vimeo text-uppercase">Watch Video <span
								class="icon-arrow-right small"></span></a>
						</p>
					</div>
				</div>
			</div>
		</div>





		<footer class="site-footer">
			<div class="container">


				<div class="row">
					<div class="col-md-4">
						<h3 class="footer-heading mb-4 text-white">About</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Repellat quos rem ullam, placeat amet.</p>
						<p>
							<a href="#" class="btn btn-primary pill text-white px-4">Read
								More</a>
						</p>
					</div>
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-6">
								<h3 class="footer-heading mb-4 text-white">Quick Menu</h3>
								<ul class="list-unstyled">
									<li><a href="#">About</a></li>
									<li><a href="#">Services</a></li>
									<li><a href="#">Approach</a></li>
									<li><a href="#">Sustainability</a></li>
									<li><a href="#">News</a></li>
									<li><a href="#">Careers</a></li>
								</ul>
							</div>
							<div class="col-md-6">
								<h3 class="footer-heading mb-4 text-white">Categories</h3>
								<ul class="list-unstyled">
									<li><a href="#">Full Time</a></li>
									<li><a href="#">Freelance</a></li>
									<li><a href="#">Temporary</a></li>
									<li><a href="#">Internship</a></li>
								</ul>
							</div>
						</div>
					</div>


					<div class="col-md-2">
						<div class="col-md-12">
							<h3 class="footer-heading mb-4 text-white">Social Icons</h3>
						</div>
						<div class="col-md-12">
							<p>
								<a href="#" class="pb-2 pr-2 pl-0"><span
									class="icon-facebook"></span></a> <a href="#" class="p-2"><span
									class="icon-twitter"></span></a> <a href="#" class="p-2"><span
									class="icon-instagram"></span></a> <a href="#" class="p-2"><span
									class="icon-vimeo"></span></a>

							</p>
						</div>
					</div>
				</div>
				<div class="row pt-5 mt-5 text-center">
					<div class="col-md-12">
						<p>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<!-- <script data-cfasync="false"
								src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script> -->
							<script>document.write(new Date().getFullYear());</script>
							All Rights Reserved | This template is made with <i
								class="icon-heart text-warning" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>

				</div>
			</div>
		</footer>
	</div>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/jquery-migrate-3.0.1.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.stellar.min.js"></script>
	<script src="js/jquery.countdown.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/bootstrap-datepicker.min.js"></script>
	<script src="js/aos.js"></script>


	<script src="js/mediaelement-and-player.min.js"></script>

	<script src="js/main.js"></script>


	<script>
      document.addEventListener('DOMContentLoaded', function() {
                var mediaElements = document.querySelectorAll('video, audio'), total = mediaElements.length;

                for (var i = 0; i < total; i++) {
                    new MediaElementPlayer(mediaElements[i], {
                        pluginPath: 'https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/',
                        shimScriptAccess: 'always',
                        success: function () {
                            var target = document.body.querySelectorAll('.player'), targetTotal = target.length;
                            for (var j = 0; j < targetTotal; j++) {
                                target[j].style.visibility = 'visible';
                            }
                  }
                });
                }
            });
    </script>


	<script>
      // This example displays an address form, using the autocomplete feature
      // of the Google Places API to help users fill in the information.

      // This example requires the Places library. Include the libraries=places
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

      var placeSearch, autocomplete;
       var componentForm = {
        street_number: 'short_name',
        route: 'long_name',
        locality: 'long_name',
        administrative_area_level_1: 'short_name',
        country: 'long_name',
        postal_code: 'short_name'
      }; 

      function initAutocomplete() {
        // Create the autocomplete object, restricting the search to geographical
        // location types.
        autocomplete = new google.maps.places.Autocomplete(
            /** @type {!HTMLInputElement} */(document.getElementById('autocomplete')),
            {types: ['geocode']});

        // When the user selects an address from the dropdown, populate the address
        // fields in the form.
        autocomplete.addListener('place_changed', fillInAddress);
      }

      function fillInAddress() {
        // Get the place details from the autocomplete object.
        var place = autocomplete.getPlace();

        for (var component in componentForm) {
          document.getElementById(component).value = '';
          document.getElementById(component).disabled = false;
        }

        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
        for (var i = 0; i < place.address_components.length; i++) {
          var addressType = place.address_components[i].types[0];
          if (componentForm[addressType]) {
            var val = place.address_components[i][componentForm[addressType]];
            document.getElementById(addressType).value = val;
          }
        }
      }

      // Bias the autocomplete object to the user's geographical location,
      // as supplied by the browser's 'navigator.geolocation' object.
      function geolocate() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var geolocation = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            var circle = new google.maps.Circle({
              center: geolocation,
              radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
          });
        }
      }
    </script>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&libraries=places&callback=initAutocomplete"
		async defer></script>

</body>
</html>