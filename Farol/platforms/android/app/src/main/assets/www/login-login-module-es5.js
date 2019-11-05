(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["login-login-module"],{

/***/ "./node_modules/raw-loader/index.js!./src/app/login/login.page.html":
/*!*****************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/login/login.page.html ***!
  \*****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-content>\n    <div class=\"ion-padding\">\n        <div class=\"logo\">\n            <ion-img src=\"assets/farol5.png\"></ion-img>\n            <div class=\"slogan\">\n                <ion-label>A luz para seu estudo!</ion-label>\n            </div>\n        </div>\n        <div class=\"login\">\n            Usuário\n            <ion-input name=\"user\" [(ngModel)]=\"user\" type=\"text\" id=\"user\"></ion-input>\n            Senha\n            <ion-input name=\"password\" [(ngModel)]=\"password\" type=\"password\" id=\"pass\"></ion-input>\n        </div>\n        <div class=\"botao\">\n            <ion-button (click)=\"loginUser()\">Logar</ion-button>\n        </div>\n        <div class=\"criarconta\">\n            <ion-label>Não tem conta?<span><a href=\"/register\">Clique Aqui!</a></span></ion-label>\n        </div>\n    </div>\n</ion-content>"

/***/ }),

/***/ "./src/app/login/login.module.ts":
/*!***************************************!*\
  !*** ./src/app/login/login.module.ts ***!
  \***************************************/
/*! exports provided: LoginPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginPageModule", function() { return LoginPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _login_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./login.page */ "./src/app/login/login.page.ts");







var routes = [
    {
        path: '',
        component: _login_page__WEBPACK_IMPORTED_MODULE_6__["LoginPage"]
    }
];
var LoginPageModule = /** @class */ (function () {
    function LoginPageModule() {
    }
    LoginPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes)
            ],
            declarations: [_login_page__WEBPACK_IMPORTED_MODULE_6__["LoginPage"]]
        })
    ], LoginPageModule);
    return LoginPageModule;
}());



/***/ }),

/***/ "./src/app/login/login.page.scss":
/*!***************************************!*\
  !*** ./src/app/login/login.page.scss ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "ion-title {\n  font-size: 20px;\n  text-align: center;\n}\n\n.ion-padding {\n  margin: 0;\n  padding: 0;\n  outline: 0;\n  box-sizing: border-box;\n}\n\n.logo {\n  text-align: center;\n  font-size: 12px;\n  width: 250px;\n  height: 250px;\n  margin: auto;\n  margin-top: 40px;\n}\n\n.logo img {\n  margin-bottom: 5px;\n}\n\n.slogan {\n  margin-top: 20px;\n}\n\n.slogan ion-label {\n  font-size: 12px;\n  font-style: italic;\n}\n\n.login {\n  margin-top: 30px;\n  text-align: center;\n}\n\n.login ion-input {\n  margin: auto;\n  margin-top: 10px;\n  margin-bottom: 10px;\n  border-radius: 10px;\n  text-align: center;\n  width: 50%;\n  border: 1px solid black;\n}\n\n.botao {\n  text-align: center;\n  margin-top: 10px;\n}\n\n.botao ion-button {\n  border-radius: 10px;\n  width: 50%;\n}\n\n.botao ion-button:hover {\n  background-color: gray;\n}\n\n.criarconta {\n  font-size: 12px;\n  margin-top: 10px;\n  text-align: center;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL2lzbWFlbGtvZnJlL8OBcmVhIGRlIFRyYWJhbGhvL0Zhcm9sL0Zhcm9sX0xhbmd1YWdlX1RyYWluaW5nL0Zhcm9sL3NyYy9hcHAvbG9naW4vbG9naW4ucGFnZS5zY3NzIiwic3JjL2FwcC9sb2dpbi9sb2dpbi5wYWdlLnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDSSxlQUFBO0VBQ0Esa0JBQUE7QUNDSjs7QURDQTtFQUNJLFNBQUE7RUFDQSxVQUFBO0VBQ0EsVUFBQTtFQUNBLHNCQUFBO0FDRUo7O0FEQUE7RUFDSSxrQkFBQTtFQUNBLGVBQUE7RUFDQSxZQUFBO0VBQ0EsYUFBQTtFQUNBLFlBQUE7RUFDQSxnQkFBQTtBQ0dKOztBRERJO0VBQ0ksa0JBQUE7QUNHUjs7QURBQTtFQUNJLGdCQUFBO0FDR0o7O0FERkk7RUFDSSxlQUFBO0VBQ0Esa0JBQUE7QUNJUjs7QUREQTtFQUNJLGdCQUFBO0VBQ0Esa0JBQUE7QUNJSjs7QURISTtFQUNJLFlBQUE7RUFDQSxnQkFBQTtFQUNBLG1CQUFBO0VBQ0EsbUJBQUE7RUFDQSxrQkFBQTtFQUNBLFVBQUE7RUFDQSx1QkFBQTtBQ0tSOztBREZBO0VBQ0ksa0JBQUE7RUFDQSxnQkFBQTtBQ0tKOztBREpJO0VBQ0ksbUJBQUE7RUFDQSxVQUFBO0FDTVI7O0FESkk7RUFDSSxzQkFBQTtBQ01SOztBRERBO0VBQ0ksZUFBQTtFQUNBLGdCQUFBO0VBQ0Esa0JBQUE7QUNJSiIsImZpbGUiOiJzcmMvYXBwL2xvZ2luL2xvZ2luLnBhZ2Uuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbImlvbi10aXRsZXtcbiAgICBmb250LXNpemU6IDIwcHg7XG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xufVxuLmlvbi1wYWRkaW5ne1xuICAgIG1hcmdpbjogMDtcbiAgICBwYWRkaW5nOiAwO1xuICAgIG91dGxpbmU6IDA7XG4gICAgYm94LXNpemluZzogYm9yZGVyLWJveDtcbn1cbi5sb2dve1xuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgICBmb250LXNpemU6IDEycHg7XG4gICAgd2lkdGg6IDI1MHB4O1xuICAgIGhlaWdodDogMjUwcHg7XG4gICAgbWFyZ2luOiBhdXRvO1xuICAgIG1hcmdpbi10b3A6IDQwcHg7XG5cbiAgICBpbWd7XG4gICAgICAgIG1hcmdpbi1ib3R0b206IDVweDtcbiAgICB9ICBcbn0gIFxuLnNsb2dhbntcbiAgICBtYXJnaW4tdG9wOiAyMHB4O1xuICAgIGlvbi1sYWJlbHtcbiAgICAgICAgZm9udC1zaXplOiAxMnB4OyAgXG4gICAgICAgIGZvbnQtc3R5bGU6IGl0YWxpYztcbiAgICB9XG59XG4ubG9naW57XG4gICAgbWFyZ2luLXRvcDogMzBweDsgICAgXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xuICAgIGlvbi1pbnB1dHtcbiAgICAgICAgbWFyZ2luOiBhdXRvO1xuICAgICAgICBtYXJnaW4tdG9wOiAxMHB4O1xuICAgICAgICBtYXJnaW4tYm90dG9tOiAxMHB4O1xuICAgICAgICBib3JkZXItcmFkaXVzOiAxMHB4O1xuICAgICAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XG4gICAgICAgIHdpZHRoOiA1MCU7XG4gICAgICAgIGJvcmRlcjogMXB4IHNvbGlkIGJsYWNrOyAgICAgICAgICAgICAgICBcbiAgICB9XG59XG4uYm90YW97XG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xuICAgIG1hcmdpbi10b3A6IDEwcHg7XG4gICAgaW9uLWJ1dHRvbnsgICAgXG4gICAgICAgIGJvcmRlci1yYWRpdXM6IDEwcHg7XG4gICAgICAgIHdpZHRoOiA1MCU7ICAgICAgICBcbiAgICB9XG4gICAgaW9uLWJ1dHRvbjpob3ZlcntcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogZ3JheTtcbiAgICB9XG59XG4gICAgXG5cbi5jcmlhcmNvbnRhe1xuICAgIGZvbnQtc2l6ZTogMTJweDtcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcbn0iLCJpb24tdGl0bGUge1xuICBmb250LXNpemU6IDIwcHg7XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbn1cblxuLmlvbi1wYWRkaW5nIHtcbiAgbWFyZ2luOiAwO1xuICBwYWRkaW5nOiAwO1xuICBvdXRsaW5lOiAwO1xuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xufVxuXG4ubG9nbyB7XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgZm9udC1zaXplOiAxMnB4O1xuICB3aWR0aDogMjUwcHg7XG4gIGhlaWdodDogMjUwcHg7XG4gIG1hcmdpbjogYXV0bztcbiAgbWFyZ2luLXRvcDogNDBweDtcbn1cbi5sb2dvIGltZyB7XG4gIG1hcmdpbi1ib3R0b206IDVweDtcbn1cblxuLnNsb2dhbiB7XG4gIG1hcmdpbi10b3A6IDIwcHg7XG59XG4uc2xvZ2FuIGlvbi1sYWJlbCB7XG4gIGZvbnQtc2l6ZTogMTJweDtcbiAgZm9udC1zdHlsZTogaXRhbGljO1xufVxuXG4ubG9naW4ge1xuICBtYXJnaW4tdG9wOiAzMHB4O1xuICB0ZXh0LWFsaWduOiBjZW50ZXI7XG59XG4ubG9naW4gaW9uLWlucHV0IHtcbiAgbWFyZ2luOiBhdXRvO1xuICBtYXJnaW4tdG9wOiAxMHB4O1xuICBtYXJnaW4tYm90dG9tOiAxMHB4O1xuICBib3JkZXItcmFkaXVzOiAxMHB4O1xuICB0ZXh0LWFsaWduOiBjZW50ZXI7XG4gIHdpZHRoOiA1MCU7XG4gIGJvcmRlcjogMXB4IHNvbGlkIGJsYWNrO1xufVxuXG4uYm90YW8ge1xuICB0ZXh0LWFsaWduOiBjZW50ZXI7XG4gIG1hcmdpbi10b3A6IDEwcHg7XG59XG4uYm90YW8gaW9uLWJ1dHRvbiB7XG4gIGJvcmRlci1yYWRpdXM6IDEwcHg7XG4gIHdpZHRoOiA1MCU7XG59XG4uYm90YW8gaW9uLWJ1dHRvbjpob3ZlciB7XG4gIGJhY2tncm91bmQtY29sb3I6IGdyYXk7XG59XG5cbi5jcmlhcmNvbnRhIHtcbiAgZm9udC1zaXplOiAxMnB4O1xuICBtYXJnaW4tdG9wOiAxMHB4O1xuICB0ZXh0LWFsaWduOiBjZW50ZXI7XG59Il19 */"

/***/ }),

/***/ "./src/app/login/login.page.ts":
/*!*************************************!*\
  !*** ./src/app/login/login.page.ts ***!
  \*************************************/
/*! exports provided: LoginPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginPage", function() { return LoginPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _services_firebase_connection_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/firebase-connection.service */ "./src/app/services/firebase-connection.service.ts");
/* harmony import */ var _angular_fire_auth__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/fire/auth */ "./node_modules/@angular/fire/auth/index.js");





// import { FirebaseConnectionService } from '../services/firebase-connection.service';
var LoginPage = /** @class */ (function () {
    function LoginPage(navCtrl, menu, firebase, fbAuth) {
        this.navCtrl = navCtrl;
        this.menu = menu;
        this.firebase = firebase;
        this.fbAuth = fbAuth;
        this.errorMessage = '';
        this.menu.enable(false);
        this.fbAuth.auth.onAuthStateChanged(function (user) {
            if (user) {
                navCtrl.navigateRoot('/home');
            }
            else {
                alert('usuário não logado');
            }
        });
    }
    LoginPage.prototype.loginUser = function () {
        var _this = this;
        this.firebase.loginUser(this.user, this.password)
            .then(function (res) {
            console.log(res);
            _this.errorMessage = "";
            _this.navCtrl.navigateRoot('/home');
            _this.meuOvo = _this.firebase.userDetails();
            console.log(_this.meuOvo.uid);
        }, function (err) {
            _this.errorMessage = err.message;
            // alert(this.errorMessage);      
            alert('Usuário ou Senha inválidos');
        });
    };
    LoginPage.prototype.ngOnInit = function () {
    };
    LoginPage.ctorParameters = function () { return [
        { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_2__["NavController"] },
        { type: _ionic_angular__WEBPACK_IMPORTED_MODULE_2__["MenuController"] },
        { type: _services_firebase_connection_service__WEBPACK_IMPORTED_MODULE_3__["FirebaseConnectionService"] },
        { type: _angular_fire_auth__WEBPACK_IMPORTED_MODULE_4__["AngularFireAuth"] }
    ]; };
    LoginPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-login',
            template: __webpack_require__(/*! raw-loader!./login.page.html */ "./node_modules/raw-loader/index.js!./src/app/login/login.page.html"),
            styles: [__webpack_require__(/*! ./login.page.scss */ "./src/app/login/login.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_ionic_angular__WEBPACK_IMPORTED_MODULE_2__["NavController"],
            _ionic_angular__WEBPACK_IMPORTED_MODULE_2__["MenuController"],
            _services_firebase_connection_service__WEBPACK_IMPORTED_MODULE_3__["FirebaseConnectionService"],
            _angular_fire_auth__WEBPACK_IMPORTED_MODULE_4__["AngularFireAuth"]])
    ], LoginPage);
    return LoginPage;
}());



/***/ })

}]);
//# sourceMappingURL=login-login-module-es5.js.map