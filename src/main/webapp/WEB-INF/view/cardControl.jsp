<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script>
	function deleteCard(cardname){
	      axios.delete("http://localhost:8000/deleteCard/"+cardname)
	      .then(resData => {
	                 console.log(resData);
	      }).catch(error => {
					console.log("비정상 응답 ", error);
	      });
	      alert("삭제완료되었습니다.");
	      document.location.reload();
	}
</script>
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
                <div class="mb-0 site-logo"><a href="admin.html">Admin<br> Card<strong class="font-weight-bold">Fit</strong></a></div>
              </div>
              <div class="col-10">
                <nav class="site-navigation text-right" role="navigation">
                  <div class="container">
                    <ul class="site-menu js-clone-nav d-none d-lg-block">
                      
                      <li><a href="/createCard">카드추가</a></li>
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
						<h2 style="color: white">카드 검색하기</h2>
						<br>
						<form action="/cardControl" method="post">
							<div class="row mb-3">
								<div class="col-md-9">
									<div class="row">
										<div class="col-md-6 mb-3 mb-md-0">
											<div class="input-wrap">
<input type="text"	class="form-control form-control-block search-input  border-0 px-4"
													name="cardname" placeholder="카드 이름을 입력해주세요">
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-3">
									<input type="submit"
										class="btn btn-search btn-primary btn-block" value="Search">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<br>
		<br>
		<br>

 		<c:choose>
            <c:when test="${empty card}">
            <tr>
               <td colspan="5">
                  <p align="center">
                     <b><span style="font-size: 13pt;">검색된 카드가 없습니다.</span></b>
                  </p>
               </td>
            </tr>
         </c:when>
         <c:otherwise>
         <c:forEach items="${card}" var="card">
         
           <div class="container-fluid">
           <div class="row">
             <div class="col-md-15 mx-auto text-center mb-10 section-heading">

         <br><br>
         
        <table frame="void">
	     <td>
	     <img src="images/${card.bankname}${card.cardname}.png" width="350" height="200">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	     </td>
         <td>
         
         <table class="table table-bordered">
            
            
            <tr>
                <td>은행명 </td>
                <td colspan="2" width="1100" height="50" >${card.bankname}</td>
            </tr>
            <tr>
                <td>카드이름 </td>
                <td colspan="2">${card.cardname}</td>
            </tr>
            
            <tr>
                <td>이용조건</td>
                <td colspan="2">                <c:if test="${card.condition != 'null' && card.condition != null }">
                ${card.condition}<br>
                </c:if></td>
            </tr>
            <tr>
            <tr>
                <td rowspan="18">혜택 </td>
                <tr>
                <td colspan="2">
                <c:if test="${card.benefit.movie != 'null' && card.benefit.movie != null}">
                ${card.benefit.movie}<br>
                </c:if>
                <c:if test="${card.benefit.cafe != 'null' && card.benefit.cafe != null}">
                ${card.benefit.cafe}<br>
                </c:if>
                <c:if test="${card.benefit.offshop != 'null' && card.benefit.offshop != null}">
                ${card.benefit.offshop}<br>
                </c:if>
                <c:if test="${card.benefit.onshop != 'null' && card.benefit.onshop != null}">
                ${card.benefit.onshop}<br>
                </c:if>
                <c:if test="${card.benefit.telecom != 'null' && card.benefit.telecom != null}">
                ${card.benefit.telecom}<br>
                </c:if>
                <c:if test="${card.benefit.transportation != 'null' && card.benefit.transportation != null}">
                ${card.benefit.transportation}<br>
                </c:if>
                <c:if test="${card.benefit.food != 'null' && card.benefit.food != null}">
                ${card.benefit.food}<br>
                </c:if>
                <c:if test="${card.benefit.others != 'null' && card.benefit.others != null}">
                ${card.benefit.others}<br>
                </c:if>
                </td>
                </tr>
			</table>
			
								<p align="right">
						<a href="updateCard/${card.cardname}"><span style="font-size: 15pt;">수정하기</span></a>
					</p>

					
					<p align="right">
						<a  href="javascript:deleteCard('${card.cardname}')"><span style="font-size: 15pt;">삭제하기</span></a>
					</p>
		</td>
         </table>
         <br><hr><br>
</div>
</div>
</div>
         </c:forEach>
  <br><br><br>

            </c:otherwise>
      </c:choose>

		

		<br>
		<br>
		<br>


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
							<script data-cfasync="false"
								src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
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