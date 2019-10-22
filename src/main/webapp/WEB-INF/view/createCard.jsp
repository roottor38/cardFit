<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	display: block;
	width: 100%
}
</style>
</head>

<script type="text/javascript">
function check(){
	var cardname = document.getElementById("cardname").value;
	var address = document.getElementById("address").value;
	if(cardname==null || cardname.length==0 || cardname==""||
			address==""){
		alert("필수 입력정보를 확인해주세요.");
		return false;
	}else{
		alert("카드가 등록되었습니다.")
    	return true; 
	}
}
</script>

<body>

	<div class="site-section">
		<div class="container">
			<div class="row">
				<div class="col-md-6 mx-auto text-center mb-5 section-heading">
					<h2 class="mb-5" style="margin: 5px;">카드 등록하기</h2>
				</div>
			</div>


			<!--카드등록-->
			<br> <br> <br>

			<form action="/createCard" method="post" encType="multipart/form-data" onsubmit="return check()">
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>은행</td>
							<td colspan="2">												<select
													class="form-control"
													name="bankname">
													<option value="신한은행">신한은행</option>
													<option value="우리은행">우리은행</option>
													<option value="국민은행">국민은행</option>
												</select></td>
						</tr>
						<tr>
							<td>카드이름</td>
							<td colspan="2"><input type="text" placeholder="체크카드이름 " id="cardname"
								name="cardname" class="form-control" />
							 *입력필수</td>
						</tr>
						<tr>
							<td>이용조건</td>
							<td colspan="2"><input type="text" placeholder="전월 실적"
								name="condition" class="form-control" /></td>
						</tr>
						<tr>
							<td>사진파일</td>
							<td colspan="2">
							<input type="file" name="address" id="address"
								class="form-control"  accept=".jpg, .jpeg, .png"/>
							 *입력필수
							</td>
						</tr>
						<tr>
							<td>영화</td>
							<td colspan="2"><input type="text" 
								name="movie" class="form-control" /></td>
						</tr>
												<tr>
							<td>카페</td>
							<td colspan="2"><input type="text" 
								name="cafe" class="form-control" /></td>
						</tr>
												<tr>
							<td>대중교통</td>
							<td colspan="2"><input type="text" 
								name="transportation" class="form-control" /></td>
						</tr>
												<tr>
							<td>통신사</td>
							<td colspan="2"><input type="text" 
								name="telecom" class="form-control" /></td>
						</tr>
												<tr>
							<td>쇼핑</td>
							<td colspan="2"><input type="text" 
								name="offshop" class="form-control" /></td>
						</tr>
												<tr>
							<td>온라인쇼핑</td>
							<td colspan="2"><input type="text" 
								name="onshop" class="form-control" /></td>
						</tr>
												<tr>
							<td>외식</td>
							<td colspan="2"><input type="text" 
								name="food" class="form-control" /></td>
						</tr>
												<tr>
							<td>기타</td>
							<td colspan="2"><input type="text" 
								name="others" class="form-control" /></td>
						</tr>
					</tbody>
				</table>

				<input type="submit" class="btn btn-search btn-primary btn-block"
					value="register" >

			</form>


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
			document
					.addEventListener(
							'DOMContentLoaded',
							function() {
								var mediaElements = document
										.querySelectorAll('video, audio'), total = mediaElements.length;

								for (var i = 0; i < total; i++) {
									new MediaElementPlayer(
											mediaElements[i],
											{
												pluginPath : 'https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/',
												shimScriptAccess : 'always',
												success : function() {
													var target = document.body
															.querySelectorAll('.player'), targetTotal = target.length;
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
				street_number : 'short_name',
				route : 'long_name',
				locality : 'long_name',
				administrative_area_level_1 : 'short_name',
				country : 'long_name',
				postal_code : 'short_name'
			};

			function initAutocomplete() {
				// Create the autocomplete object, restricting the search to geographical
				// location types.
				autocomplete = new google.maps.places.Autocomplete(
				/** @type {!HTMLInputElement} */
				(document.getElementById('autocomplete')), {
					types : [ 'geocode' ]
				});

				// When the user selects an address from the dropdown, populate the address
				// fields in the form.
				autocomplete.addListener('place_changed', fillInAddress);
			}

			function fillInAddress() {
				// Get the place details from the autocomplete object.
				var place = autocomplete.getPlace();

				for ( var component in componentForm) {
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
					navigator.geolocation
							.getCurrentPosition(function(position) {
								var geolocation = {
									lat : position.coords.latitude,
									lng : position.coords.longitude
								};
								var circle = new google.maps.Circle({
									center : geolocation,
									radius : position.coords.accuracy
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