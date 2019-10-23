<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>
    <title>Welcome CardFit</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700|Work+Sans:300,400,700" rel="stylesheet">
    <link rel="stylesheet" href="fonts/icomoon/style.css">

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/animate.css">
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/mediaelementplayer.min.css">
    
    
    
    <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
  
    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/style.css">
    <style>
    .button {
        background-color: #b4b4b4;
        padding: 10px;
        border: #b4b4b4;
        box-sizing: inherit;
        color: white;
        text-decoration: none;
        text-align: center;
        cursor: pointer;
        display:block;
        width:100%
    }
    #pic{
    	margin: 0px 500px 0px 380px;
    
    }

   </style>
  </head>

  <body>
    <div class="site-section">
      <div class="container">
        <div class="row">
          <div class="col-md-6 mx-auto text-center mb-5 section-heading">
            <h2 class="mb-5" style="margin: 5px;">카드 수정하기</h2>
          </div>
        </div>
      
 
 <!--카드등록-->
<br><br><br>
    
        <form action="/updateCard" method="post" encType="multiplart/form-data">


		     <img  id="pic" src="../images/${card[0].bankname}${card[0].cardname}.png" width="350" height="200"><br>
<br>


        	 <table class="table table-bordered">

	            <tr>
	                <td>은행명 </td>
	                <td colspan="2" width="1000" height="50">${card[0].bankname}
	                <input type="hidden" name="bankname" value="${card[0].bankname}"></td>
	            </tr>
	            <tr>
	                <td>카드이름 </td>
	                <td colspan="2">${card[0].cardname}
	                <input type="hidden" name="cardname" value="${card[0].cardname}"></td>
	            </tr>
	            
	            <c:choose>
            		<c:when test="${card[0].condition eq 'null'}">
            			            <tr>
	                <td>이용조건</td>
	                 <td colspan="2"><input type="text" value="" name="condition" class="form-control"/></td>
	           		 </tr>
            		</c:when>
            		<c:otherwise>
            		            <tr>
	                <td>이용조건</td>
	                 <td colspan="2"><input type="text" value="${card[0].condition}" name="condition" class="form-control"/></td>
	            </tr>
            		</c:otherwise>
            	</c:choose>
            	
            	
            		            <c:choose>
            		<c:when test="${card[0].benefit.movie eq 'null'}">
            			            <tr>
	                <td>영화</td>
	                 <td colspan="2"><input type="text"  value="" name="movie" class="form-control"/></td>
	           		 </tr>
            		</c:when>
            		<c:otherwise>
            		            <tr>
	                <td>영화</td>
	                 <td colspan="2"><input type="text" value="${card[0].benefit.movie}" name="movie" class="form-control"/></td>
	            </tr>
            		</c:otherwise>
            	</c:choose>
            	
            	         		            <c:choose>
            		<c:when test="${card[0].benefit.cafe eq 'null'}">
            			            <tr>
	                <td>카페</td>
	                 <td colspan="2"><input type="text"  value="" name="cafe" class="form-control"/></td>
	           		 </tr>
            		</c:when>
            		<c:otherwise>
            		            <tr>
	                <td>카페</td>
	                 <td colspan="2"><input type="text" value="${card[0].benefit.cafe}"  name="cafe" class="form-control"/></td>
	            </tr>
            		</c:otherwise>
            	</c:choose>
            	            	         		            <c:choose>
            		<c:when test="${card[0].benefit.offshop eq 'null'}">
            			            <tr>
	                <td>쇼핑</td>
	                 <td colspan="2"><input type="text"  value=""  name="offshop" class="form-control"/></td>
	           		 </tr>
            		</c:when>
            		<c:otherwise>
            		            <tr>
	                <td>쇼핑</td>
	                 <td colspan="2"><input type="text" value="${card[0].benefit.offshop}" name="offshop" class="form-control"/></td>
	            </tr>
            		</c:otherwise>
            	</c:choose>
            	 <c:choose>
            		<c:when test="${card[0].benefit.onshop eq 'null'}">
            			            <tr>
	                <td>온라인쇼핑</td>
	                 <td colspan="2"><input type="text"  value=""  name="onshop" class="form-control"/></td>
	           		 </tr>
            		</c:when>
            		<c:otherwise>
            		            <tr>
	                <td>온라인쇼핑</td>
	                 <td colspan="2"><input type="text" value="${card[0].benefit.onshop}" name="onshop" class="form-control"/></td>
	            </tr>
            		</c:otherwise>
            	</c:choose>
            	
             	 <c:choose>
            		<c:when test="${card[0].benefit.telecom eq 'null'}">
            			            <tr>
	                <td>통신사</td>
	                 <td colspan="2"><input type="text" value=""  name="telecom" class="form-control"/></td>
	           		 </tr>
            		</c:when>
            		<c:otherwise>
            		            <tr>
	                <td>통신사</td>
	                 <td colspan="2"><input type="text" value="${card[0].benefit.telecom}" name="telecom" class="form-control"/></td>
	            </tr>
            		</c:otherwise>
            	</c:choose>
             	 <c:choose>
            		<c:when test="${card[0].benefit.transportation eq 'null'}">
            			            <tr>
	                <td>대중교통</td>
	                 <td colspan="2"><input type="text"   value="" name="transportation" class="form-control"/></td>
	           		 </tr>
            		</c:when>
            		<c:otherwise>
            		            <tr>
	                <td>대중교통</td>
	                 <td colspan="2"><input type="text" value="${card[0].benefit.transportation}" name="transportation" class="form-control"/></td>
	            </tr>
            		</c:otherwise>
            	</c:choose>
             	 <c:choose>
            		<c:when test="${card[0].benefit.food eq 'null'}">
            			            <tr>
	                <td>외식</td>
	                 <td colspan="2"><input type="text"  value=""  name="food" class="form-control"/></td>
	           		 </tr>
            		</c:when>
            		<c:otherwise>
            		            <tr>
	                <td>외식</td>
	                 <td colspan="2"><input type="text" value="${card[0].benefit.food}" name="food" class="form-control"/></td>
	            </tr>
            		</c:otherwise>
            	</c:choose>
            	             	 <c:choose>
            		<c:when test="${card[0].benefit.others eq 'null'}">
            			            <tr>
	                <td>기타</td>
	                 <td colspan="2"><input type="text" value="" name="others" class="form-control"/></td>
	           		 </tr>
            		</c:when>
            		<c:otherwise>
            		            <tr>
	                <td>기타</td>
	                 <td colspan="2"><input type="text" value="${card[0].benefit.others}" name="others" class="form-control"/></td>
	            </tr>
            		</c:otherwise>
            	</c:choose>


			</table>
 
 		<input type="submit" class="btn btn-search btn-primary btn-block" value="update">
 </form>
 
 </div>
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

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&libraries=places&callback=initAutocomplete"
        async defer></script>

  </body>
</html>