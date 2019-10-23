<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Welcome CardFit</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700|Work+Sans:300,400,700" rel="stylesheet">
    <link rel="stylesheet" href="fonts/icomoon/style.css">

    <link rel="stylesheet" href="css/bootstrap.min.css">
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
  </head>
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  <script type="text/javascript">

  
  function histogram(){
      axios.post("http://localhost:8000/histogram/total")
      .then(resData => {
                 console.log(resData);
      }).catch(error => {
            console.log("비정상 응답 ", error);
      });
      document.getElementById("printhistogram").innerHTML ="<img src=\"../images/histogram.png\" width = 550px height = 400px >";
}
  
  function Genderhistogram(){
      axios.post("http://localhost:8000/histogram/gender")
      .then(resData => {
                 console.log(resData);
      }).catch(error => {
            console.log("비정상 응답 ", error);
      });
      document.getElementById("printhistogram").innerHTML ="<img src=\"../images/Genderhistogram.png\" width = 600px height = 400px >";
}
  
  function Agehistogram(){
      axios.post("http://localhost:8000/histogram/age")
      .then(resData => {
                 console.log(resData);
      }).catch(error => {
            console.log("비정상 응답 ", error);
      });
      document.getElementById("printhistogram").innerHTML ="<img src=\"../images/Agehistogram.png\" width = 600px height = 400px >";
}
  </script>
  <style type="text/css">
       .studentInfo {
           width: 500px;
           border-collapse: collapse;
       }
 
           .studentInfo td {
               border: 1px solid black;
           }
 
           .studentInfo tr:nth-child(even) {
               background-color: #E4FFB7;
           }
 
           .studentInfo tr:nth-child(odd) {
                background-color: #EFFFD2;
           }
 
 
       .header {
           text-align: center;
           font-weight: bolder;
           background-color: #80B327;
           color: white;
       }
       
       #mydata{
       margin: 0px 450px 0px 300px;
       }
       
/       #printhistogram{
       margin: 0px 450px 10px 10px;
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
    </div> <!-- .site-mobile-menu -->
    
    
   <div class="site-navbar-wrap js-site-navbar bg-white">
         <div class="container">
            <div class="site-navbar bg-light">
               <div class="py-1">
                  <div class="row align-items-center">
                     <div class="col-2">
                        <div class="mb-0 site-logo">
                           <a href="/index">Card<strong class="font-weight-bold">Fit</strong>
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
                                 <li><a href="/insight">인사이트</a></li>
                                 <li><a href="/myCardBenefit">내 카드 혜택보기</a></li>
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

    <div class="unit-5 overlay" style="background-image: url('images/hero_1.jpg');">
      <div class="container text-center">
        <h2 class="mb-0">인사이트</h2>
        <p class="mb-0 unit-6"><a href="index.html">데이터 분석</a></p>
      </div>
    </div>

    
    

  <br><br><br>
  
  
      <div  class="container text-center">
  <table id="mydata" class="studentInfo">
        <tbody><tr>
            <td class="header" colspan="6">체크박스 조회 횟수</td>
        </tr>
        <tr class="mainRow">
            <td>연령/성별</td>
            <td>영화</td>
         <td>카페</td>
         <td>통신사</td>
         <td>대중교통</td>
         <td>쇼핑</td>
         <td>온라인쇼핑</td>
         <td>외식</td>
         <td>기타</td>
        </tr>
        <tr class="altRow">
            <td>20대 남성</td>
            <td>${data[0].movie}</td>
            <td>${data[0].cafe}</td>
            <td>${data[0].telecom}</td>
            <td>${data[0].transportation}</td>
            <td>${data[0].onshop}</td>
            <td>${data[0].offshop}</td>
            <td>${data[0].food}</td>
             <td>${data[0].others}</td>  
        </tr>
        <tr>
            <td>30대 남성</td>
            <td>${data[1].movie}</td>
            <td>${data[1].cafe}</td>
            <td>${data[1].telecom}</td>
            <td>${data[1].transportation}</td>
            <td>${data[1].onshop}</td>
            <td>${data[1].offshop}</td>
            <td>${data[1].food}</td>
             <td>${data[1].others}</td>  
        </tr>
        <tr>
            <td>40대 남성</td>
             <td>${data[2].movie}</td>
            <td>${data[2].cafe}</td>
            <td>${data[2].telecom}</td>
            <td>${data[2].transportation}</td>
            <td>${data[2].onshop}</td>
            <td>${data[2].offshop}</td>
            <td>${data[2].food}</td>
             <td>${data[2].others}</td>  
        </tr>
        <tr>
            <td>20대 여성</td>
            <td>${data[3].movie}</td>
            <td>${data[3].cafe}</td>
            <td>${data[3].telecom}</td>
            <td>${data[3].transportation}</td>
            <td>${data[3].onshop}</td>
            <td>${data[3].offshop}</td>
            <td>${data[3].food}</td>
             <td>${data[3].others}</td>  
        </tr>
        <tr>
            <td>30대 여성</td>
            <td>${data[4].movie}</td>
            <td>${data[4].cafe}</td>
            <td>${data[4].telecom}</td>
            <td>${data[4].transportation}</td>
            <td>${data[4].onshop}</td>
            <td>${data[4].offshop}</td>
            <td>${data[4].food}</td>
             <td>${data[4].others}</td>  
        </tr>        
        <tr>
            <td>40대 여성</td>
            <td>${data[5].movie}</td>
            <td>${data[5].cafe}</td>
            <td>${data[5].telecom}</td>
            <td>${data[5].transportation}</td>
            <td>${data[5].onshop}</td>
            <td>${data[5].offshop}</td>
            <td>${data[5].food}</td>
             <td>${data[5].others}</td>  
        </tr>
    </tbody>
    </table>
  <br><br>
  
    <button onclick="histogram()"> 전체 히스토그램 보기</button>
    <button onclick="Genderhistogram()"> 성별 히스토그램 보기</button>
    <button onclick="Agehistogram()"> 연령별 히스토그램 보기</button>
  <br><br>
      <p id="printhistogram"></p>  
        </div>
        
        
        
        
      
      
  <br><br><br>


    
    <footer class="site-footer">
      <div class="container">
        

        <div class="row">
          <div class="col-md-4">
            <h3 class="footer-heading mb-4 text-white">About</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellat quos rem ullam, placeat amet.</p>
            <p><a href="#" class="btn btn-primary pill text-white px-4">Read More</a></p>
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
            <div class="col-md-12"><h3 class="footer-heading mb-4 text-white">Social Icons</h3></div>
              <div class="col-md-12">
                <p>
                  <a href="#" class="pb-2 pr-2 pl-0"><span class="icon-facebook"></span></a>
                  <a href="#" class="p-2"><span class="icon-twitter"></span></a>
                  <a href="#" class="p-2"><span class="icon-instagram"></span></a>
                  <a href="#" class="p-2"><span class="icon-vimeo"></span></a>

                </p>
              </div>
          </div>
        </div>
        <div class="row pt-5 mt-5 text-center">
          <div class="col-md-12">
            <p>
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            Copyright &copy; <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script>document.write(new Date().getFullYear());</script> All Rights Reserved | This template is made with <i class="icon-heart text-warning" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank" >Colorlib</a>
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

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&libraries=places&callback=initAutocomplete"
        async defer></script>

  </body>
</html>