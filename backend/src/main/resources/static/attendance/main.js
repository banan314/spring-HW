(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/activity-list/activity-list.component.css":
/*!***********************************************************!*\
  !*** ./src/app/activity-list/activity-list.component.css ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/activity-list/activity-list.component.html":
/*!************************************************************!*\
  !*** ./src/app/activity-list/activity-list.component.html ***!
  \************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\n  <h3>Lista zajęć</h3>\n  Wybierz z listy zajęcia w których brałeś udział\n  <p><b>Twoja obecność na zajęciach: 2/8</b></p>\n  <table class=\"table table-striped\">\n    <thead>\n    <tr>\n      <th></th>\n      <th>Nazwa zajęć</th>\n      <th>Lokalizacja</th>\n      <th>Data</th>\n      <th></th>\n    </tr>\n    </thead>\n    <tbody>\n    <tr *ngFor=\"let activity of activities; let i = index\">\n      <td>{{i + 1}}</td>\n      <td>{{activity.name}}</td>\n      <td>{{activity.location}}</td>\n      <td>{{activity.startDate | date:'medium'}}</td>\n      <td>\n        <button class=\"delete\"\n                (click)=\"deleteActivity(activity); $event.stopPropagation()\"><i class=\"fa fa-times\" style=\"color: orange\"></i></button>\n      </td>\n    </tr>\n    </tbody>\n  </table>\n\n  <div class=\"row\">\n    <div class=\"col-md-6 col-md-offset-3\">\n      <h1 class=\"text-center\">Dodawanie zajęć</h1>\n      <h4 class=\"text-center\">Uzupełnij formularz aby dodać nowe zajęcia.</h4>\n      <form>\n        <div class=\"form-group\">\n          <label for=\"exampleInputFirstName\">Temat zajęć</label>\n          <input type=\"text\" class=\"form-control\" id=\"exampleInputFirstName\" placeholder=\"Temat zajęć\" [(ngModel)]=\"activity.name\" name=\"firstName\">\n        </div>\n        <div class=\"form-group\">\n          <label for=\"exampleInputLastName\">Lokalizacja</label>\n          <input type=\"text\" class=\"form-control\" id=\"exampleInputLastName\" placeholder=\"Lokalizacja\" [(ngModel)]=\"activity.location\" name=\"lastName\">\n        </div>\n\n        <div class='form-group date datepicker'>\n          <label for=\"datepicker\">Data: <i class=\"glyphicon glyphicon-calendar\"></i></label>\n          <input type='text' class=\"form-control\" id=\"datepicker\" placeholder=\"Data\" [(ngModel)]=\"activity.startDate\" name=\"startDate\">\n          <span class=\"input-group-addon\" style=\"display: none\" onclick=\"$('#datepicker').datepicker('dialog', new Date());\">\n\n       </span>\n          {{activity.startDate}}\n        </div>\n\n        <button type=\"submit\" class=\"btn btn-default center-block\" style=\"color: white; background-color: #ff7726; width: 190px;\" (click)=\"addActivity()\">Dodaj zajęcie</button>\n      </form>\n    </div>\n  </div>\n</div>\n<router-outlet></router-outlet>\n"

/***/ }),

/***/ "./src/app/activity-list/activity-list.component.ts":
/*!**********************************************************!*\
  !*** ./src/app/activity-list/activity-list.component.ts ***!
  \**********************************************************/
/*! exports provided: ActivityListComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ActivityListComponent", function() { return ActivityListComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _model_activity__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../model/activity */ "./src/app/model/activity.ts");
/* harmony import */ var _services_activity_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/activity.service */ "./src/app/services/activity.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var ActivityListComponent = /** @class */ (function () {
    function ActivityListComponent(activityService) {
        this.activityService = activityService;
        this.activity = new _model_activity__WEBPACK_IMPORTED_MODULE_1__["Activity"];
    }
    ActivityListComponent.prototype.ngOnInit = function () {
        this.getActivities();
    };
    ActivityListComponent.prototype.addActivity = function () {
        var _this = this;
        console.log('add activity');
        this.activityService.addActivity(this.activity).subscribe(function () { return _this.getActivities(); });
    };
    ActivityListComponent.prototype.getActivities = function () {
        var _this = this;
        this.activityService.getActivities()
            .subscribe(function (data) { return _this.activities = data['activities']; }); // TODO: check
    };
    ActivityListComponent.prototype.deleteActivity = function (activity) {
        var _this = this;
        this.activityService.deleteActivity(activity.id).subscribe(function (data) { return _this.getActivities(); });
    };
    ActivityListComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-activity-list',
            template: __webpack_require__(/*! ./activity-list.component.html */ "./src/app/activity-list/activity-list.component.html"),
            styles: [__webpack_require__(/*! ./activity-list.component.css */ "./src/app/activity-list/activity-list.component.css")]
        }),
        __metadata("design:paramtypes", [_services_activity_service__WEBPACK_IMPORTED_MODULE_2__["ActivityService"]])
    ], ActivityListComponent);
    return ActivityListComponent;
}());



/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _attendance_list_attendance_list_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./attendance-list/attendance-list.component */ "./src/app/attendance-list/attendance-list.component.ts");
/* harmony import */ var _activity_list_activity_list_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./activity-list/activity-list.component */ "./src/app/activity-list/activity-list.component.ts");
/* harmony import */ var _new_student_new_student_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./new-student/new-student.component */ "./src/app/new-student/new-student.component.ts");
/* harmony import */ var _nav_bar_nav_bar_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./nav-bar/nav-bar.component */ "./src/app/nav-bar/nav-bar.component.ts");
/* harmony import */ var _log_in_log_in_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./log-in/log-in.component */ "./src/app/log-in/log-in.component.ts");
/* harmony import */ var _register_register_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./register/register.component */ "./src/app/register/register.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};








var routes = [
    {
        path: 'students',
        component: _attendance_list_attendance_list_component__WEBPACK_IMPORTED_MODULE_2__["AttendanceListComponent"]
    },
    {
        path: 'activities',
        component: _activity_list_activity_list_component__WEBPACK_IMPORTED_MODULE_3__["ActivityListComponent"]
    },
    {
        path: 'newstudent',
        component: _new_student_new_student_component__WEBPACK_IMPORTED_MODULE_4__["NewStudentComponent"]
    },
    {
        path: '',
        component: _nav_bar_nav_bar_component__WEBPACK_IMPORTED_MODULE_5__["NavBarComponent"]
    },
    {
        path: 'login',
        component: _log_in_log_in_component__WEBPACK_IMPORTED_MODULE_6__["LogInComponent"]
    },
    {
        path: 'register',
        component: _register_register_component__WEBPACK_IMPORTED_MODULE_7__["RegisterComponent"]
    }
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forRoot(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\n  <h1>\n    {{title}}\n  </h1>\n  {{greetings}}\n\n  <app-footer style=\"display: block\">footer...</app-footer>\n  <router-outlet></router-outlet>\n\n</div>\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'Attendance manager';
        this.greetings = 'Sprawdź swoją obecność i dostępne zajęcia. Dodaj zajęcia lub użytkowników.';
    }
    AppComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _activity_list_activity_list_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./activity-list/activity-list.component */ "./src/app/activity-list/activity-list.component.ts");
/* harmony import */ var _new_student_new_student_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./new-student/new-student.component */ "./src/app/new-student/new-student.component.ts");
/* harmony import */ var _new_activity_new_activity_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./new-activity/new-activity.component */ "./src/app/new-activity/new-activity.component.ts");
/* harmony import */ var _footer_footer_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./footer/footer.component */ "./src/app/footer/footer.component.ts");
/* harmony import */ var _attendance_list_attendance_list_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./attendance-list/attendance-list.component */ "./src/app/attendance-list/attendance-list.component.ts");
/* harmony import */ var _nav_bar_nav_bar_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./nav-bar/nav-bar.component */ "./src/app/nav-bar/nav-bar.component.ts");
/* harmony import */ var _log_in_log_in_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./log-in/log-in.component */ "./src/app/log-in/log-in.component.ts");
/* harmony import */ var _register_register_component__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ./register/register.component */ "./src/app/register/register.component.ts");
/* harmony import */ var _services_register_service__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ./services/register.service */ "./src/app/services/register.service.ts");
/* harmony import */ var _services_activity_service__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! ./services/activity.service */ "./src/app/services/activity.service.ts");
/* harmony import */ var _services_user_service__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! ./services/user.service */ "./src/app/services/user.service.ts");
/* harmony import */ var _services_log_in_service__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! ./services/log-in.service */ "./src/app/services/log-in.service.ts");
/* harmony import */ var ngx_cookie_service__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! ngx-cookie-service */ "./node_modules/ngx-cookie-service/index.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



















var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_5__["AppComponent"],
                _activity_list_activity_list_component__WEBPACK_IMPORTED_MODULE_6__["ActivityListComponent"],
                _new_student_new_student_component__WEBPACK_IMPORTED_MODULE_7__["NewStudentComponent"],
                _new_activity_new_activity_component__WEBPACK_IMPORTED_MODULE_8__["NewActivityComponent"],
                _footer_footer_component__WEBPACK_IMPORTED_MODULE_9__["FooterComponent"],
                _attendance_list_attendance_list_component__WEBPACK_IMPORTED_MODULE_10__["AttendanceListComponent"],
                _nav_bar_nav_bar_component__WEBPACK_IMPORTED_MODULE_11__["NavBarComponent"],
                _log_in_log_in_component__WEBPACK_IMPORTED_MODULE_12__["LogInComponent"],
                _register_register_component__WEBPACK_IMPORTED_MODULE_13__["RegisterComponent"]
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_4__["AppRoutingModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_3__["HttpClientModule"]
            ],
            providers: [
                _services_register_service__WEBPACK_IMPORTED_MODULE_14__["RegisterService"],
                _services_activity_service__WEBPACK_IMPORTED_MODULE_15__["ActivityService"],
                _services_user_service__WEBPACK_IMPORTED_MODULE_16__["UserService"],
                _services_log_in_service__WEBPACK_IMPORTED_MODULE_17__["LogInService"],
                ngx_cookie_service__WEBPACK_IMPORTED_MODULE_18__["CookieService"]
            ],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_5__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/attendance-list/attendance-list.component.css":
/*!***************************************************************!*\
  !*** ./src/app/attendance-list/attendance-list.component.css ***!
  \***************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/attendance-list/attendance-list.component.html":
/*!****************************************************************!*\
  !*** ./src/app/attendance-list/attendance-list.component.html ***!
  \****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<h3>Lista obecności</h3>\n\n<div class=\"input-group input-group-sm\">\n  <span class=\"input-group-addon\"><i class=\"fa fa-search\"></i></span>\n  <input class=\"form-control\" placeholder=\"Nie działa... :(\">\n</div>\n<table class=\"table table-striped\">\n  <thead>\n  <tr>\n    <th></th>\n    <th>Imię i nazwisko</th>\n    <th>Kontakt</th>\n    <th></th>\n    <th></th>\n    <th>Zajęcia</th>\n  </tr>\n  </thead>\n  <tr *ngFor=\"let user of users; let i = index\">\n    <td class=\"col-xs-1\">{{i + 1}}</td>\n\n    <span *ngIf=\"edited && user.id === editedUser.id; then thenBlock; else elseBlock\"></span>\n    <ng-template #thenBlock>\n      <td><input name=\"username\" id=\"inputUsername\" [(ngModel)]=\"editedUser.username\"></td>\n      <td><input name=\"email\" id=\"inputEmail\" [(ngModel)]=\"editedUser.email\"></td>\n      <td>\n        <button (click)=\"acceptEdit(); $event.stopPropagation()\">\n          <i class=\"fa fa-check\" style=\"color: orange\"></i>\n        </button>\n      </td>\n      <td>\n        <button (click)=\"rejectEdit(); $event.stopPropagation()\"><i class=\"fa fa-trash-o\" style=\"color: orange\"></i></button>\n      </td>\n    </ng-template>\n    <ng-template #elseBlock>\n      <td><b>{{user.username}}</b></td>\n      <td>{{user.email}}</td>\n      <td>\n        <button (click)=\"editUser(user); $event.stopPropagation()\"><i class=\"fa fa-pencil-square-o\" style=\"color: orange\"></i></button>\n      </td>\n      <td>\n        <button class=\"delete\"\n                (click)=\"deleteUser(user); $event.stopPropagation()\"><i class=\"fa fa-times\" style=\"color: orange\"></i></button>\n      </td>\n    </ng-template>\n    <!--\n    font awesome signs:\n    - fa-check\n    - fa-times\n    - .fa-pencil-square-o\n    - .fa-trash-o\n    -->\n    <td *ngFor=\"let activity of user.activities\">{{activity}}</td>\n  </tr>\n</table>\n<span><button class=\"form-control\" style=\"width: 30px;\" (click)=\"addUser()\">+</button><input class=\"form-control\"\n                                                                                             placeholder=\"Dodaj użytkownika...\"\n                                                                                             [(ngModel)]=\"user.username\"\n                                                                                             name=\"username\"/></span>\n<router-outlet></router-outlet>\n"

/***/ }),

/***/ "./src/app/attendance-list/attendance-list.component.ts":
/*!**************************************************************!*\
  !*** ./src/app/attendance-list/attendance-list.component.ts ***!
  \**************************************************************/
/*! exports provided: AttendanceListComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AttendanceListComponent", function() { return AttendanceListComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _model_user__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../model/user */ "./src/app/model/user.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_user_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/user.service */ "./src/app/services/user.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var AttendanceListComponent = /** @class */ (function () {
    function AttendanceListComponent(router, userService) {
        this.router = router;
        this.userService = userService;
        this.user = new _model_user__WEBPACK_IMPORTED_MODULE_1__["User"];
        this.edited = false;
    }
    AttendanceListComponent.prototype.ngOnInit = function () {
        this.getUsers();
    };
    AttendanceListComponent.prototype.addUser = function () {
        var _this = this;
        console.log('adduser');
        this.userService.addUser(this.user, function () {
            _this.getUsers();
        });
    };
    AttendanceListComponent.prototype.getUsers = function () {
        var _this = this;
        this.userService.getUsers().subscribe(function (data) {
            _this.users = data['users']; // TODO: check
            if (null == data) {
                _this.router.navigate(['/login']);
            }
        });
    };
    AttendanceListComponent.prototype.deleteUser = function (user) {
        var _this = this;
        this.userService.deleteUser(user.id, function () { return _this.getUsers(); });
    };
    AttendanceListComponent.prototype.editUser = function (user) {
        this.edited = true;
        this.editedUser = user;
    };
    AttendanceListComponent.prototype.acceptEdit = function (user) {
        this.updateUser(this.editedUser);
        user = this.editedUser;
        this.finishEdit();
    };
    AttendanceListComponent.prototype.updateUser = function (user) {
        this.userService.updateUser(user.id, user);
    };
    AttendanceListComponent.prototype.rejectEdit = function () {
        this.finishEdit();
    };
    AttendanceListComponent.prototype.finishEdit = function () {
        this.edited = false;
        this.editedUser = null;
    };
    AttendanceListComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-attendance-list',
            template: __webpack_require__(/*! ./attendance-list.component.html */ "./src/app/attendance-list/attendance-list.component.html"),
            styles: [__webpack_require__(/*! ./attendance-list.component.css */ "./src/app/attendance-list/attendance-list.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"], _services_user_service__WEBPACK_IMPORTED_MODULE_3__["UserService"]])
    ], AttendanceListComponent);
    return AttendanceListComponent;
}());



/***/ }),

/***/ "./src/app/footer/footer.component.css":
/*!*********************************************!*\
  !*** ./src/app/footer/footer.component.css ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "footer {\n  display: block;\n  box-sizing: border-box;\n  width: 100%;\n  bottom: 0px;\n  left: 0px;\n\n  color: #cacaca;\n  /*https://css-tricks.com/snippets/css/fixed-footer/*/\n  position: fixed;\n  background-color: #222222;\n\n  z-index: 900;\n}\n\nfooter div {\n  /*https://css-tricks.com/centering-css-complete-guide/#center-horizontally-and-vertically*/\n  height: 75px;\n  position: relative;\n}\n\nfooter div p {\n  position: absolute;\n  top: 50%;\n  left: 50%;\n  -webkit-transform: translate(-50%, -50%);\n          transform: translate(-50%, -50%);\n}\n"

/***/ }),

/***/ "./src/app/footer/footer.component.html":
/*!**********************************************!*\
  !*** ./src/app/footer/footer.component.html ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<footer>\n  <div>\n    <p>\n      Made with <i class='fa fa-heart' style=\"color: red;\"></i> by {{companyName}}\n    </p>\n  </div>\n</footer>\n"

/***/ }),

/***/ "./src/app/footer/footer.component.ts":
/*!********************************************!*\
  !*** ./src/app/footer/footer.component.ts ***!
  \********************************************/
/*! exports provided: FooterComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FooterComponent", function() { return FooterComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var FooterComponent = /** @class */ (function () {
    function FooterComponent() {
        this.companyName = 'Kamil Banan π Łopuszański';
    }
    FooterComponent.prototype.ngOnInit = function () {
    };
    FooterComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-footer',
            template: __webpack_require__(/*! ./footer.component.html */ "./src/app/footer/footer.component.html"),
            styles: [__webpack_require__(/*! ./footer.component.css */ "./src/app/footer/footer.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], FooterComponent);
    return FooterComponent;
}());



/***/ }),

/***/ "./src/app/log-in/log-in.component.css":
/*!*********************************************!*\
  !*** ./src/app/log-in/log-in.component.css ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/log-in/log-in.component.html":
/*!**********************************************!*\
  !*** ./src/app/log-in/log-in.component.html ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"page-header\">\n  <h2>Login</h2>\n</div>\n\n<div class=\"row\">\n\n  <div class=\"col-md-6\">\n    <form class=\"form-horizontal\">\n      <div class=\"form-group\">\n        <label for=\"username\" class=\"col-sm-3 control-label\">User Name:</label>\n        <div class=\"col-sm-9\">\n          <input id=\"username\" [(ngModel)]=\"username\" type=\"text\" name=\"username\" class=\"form-control\" />\n        </div>\n      </div>\n      <div class=\"form-group\">\n        <label for=\"password\" class=\"col-sm-3 control-label\">Password:</label>\n        <div class=\"col-sm-9\">\n          <input id=\"password\" [(ngModel)]=\"password\" type=\"password\" name=\"password\" class=\"form-control\" />\n        </div>\n      </div>\n      <div class=\"form-group\">\n        <div class=\"col-sm-offset-3 col-sm-9\">\n          <div class=\"checkbox\">\n            <label>\n              <input type=\"checkbox\" [(ngModel)]=\"rememberMe\" name=\"remember\"> Remember me\n            </label>\n          </div>\n        </div>\n      </div>\n      <div class=\"form-group\">\n        <div class=\"col-sm-9 col-sm-offset-3\">\n          <input type=\"submit\" value=\"Log In\" name=\"login\" class=\"btn btn-primary\" (click)=\"login(this.username, this.password)\"/>\n        </div>\n      </div>\n    </form>\n  </div>\n\n  <div class=\"col-md-6\">\n    <p class=\"alert alert-info\">Login with user:password (to be improved).</p>\n  </div>\n\n</div>\n"

/***/ }),

/***/ "./src/app/log-in/log-in.component.ts":
/*!********************************************!*\
  !*** ./src/app/log-in/log-in.component.ts ***!
  \********************************************/
/*! exports provided: LogInComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LogInComponent", function() { return LogInComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_log_in_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/log-in.service */ "./src/app/services/log-in.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var LogInComponent = /** @class */ (function () {
    function LogInComponent(router, logInService) {
        this.router = router;
        this.logInService = logInService;
    }
    LogInComponent.prototype.ngOnInit = function () {
    };
    LogInComponent.prototype.login = function (username, password) {
        var _this = this;
        this.logInService.login(username, password)
            .subscribe(function () {
            _this.router.navigate(['/activities']);
        }, this.handleError);
    };
    LogInComponent.prototype.handleError = function (error) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    };
    LogInComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-log-in',
            template: __webpack_require__(/*! ./log-in.component.html */ "./src/app/log-in/log-in.component.html"),
            styles: [__webpack_require__(/*! ./log-in.component.css */ "./src/app/log-in/log-in.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"], _services_log_in_service__WEBPACK_IMPORTED_MODULE_2__["LogInService"]])
    ], LogInComponent);
    return LogInComponent;
}());



/***/ }),

/***/ "./src/app/model/activity.ts":
/*!***********************************!*\
  !*** ./src/app/model/activity.ts ***!
  \***********************************/
/*! exports provided: Activity */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Activity", function() { return Activity; });
var Activity = /** @class */ (function () {
    function Activity() {
    }
    Object.defineProperty(Activity.prototype, "users", {
        get: function () {
            return this._users;
        },
        set: function (value) {
            this._users = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Activity.prototype, "id", {
        get: function () {
            return this._id;
        },
        set: function (value) {
            this._id = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Activity.prototype, "name", {
        get: function () {
            return this._name;
        },
        set: function (value) {
            this._name = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Activity.prototype, "startDate", {
        get: function () {
            return this._startDate;
        },
        set: function (value) {
            this._startDate = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Activity.prototype, "location", {
        get: function () {
            return this._location;
        },
        set: function (value) {
            this._location = value;
        },
        enumerable: true,
        configurable: true
    });
    return Activity;
}());



/***/ }),

/***/ "./src/app/model/user.ts":
/*!*******************************!*\
  !*** ./src/app/model/user.ts ***!
  \*******************************/
/*! exports provided: User */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "User", function() { return User; });
var User = /** @class */ (function () {
    function User(id, username, age, sex, dateOfBirth, email, activities) {
        this._id = id;
        this._username = username;
        this._age = age;
        this._sex = sex;
        this._dateOfBirth = dateOfBirth;
        this._activities = activities;
        this._email = email;
    }
    User.fromUser = function (user) {
        return new this(user.id, user.username, user.age, user.sex, user.dateOfBirth, user.email, user.activities);
    };
    Object.defineProperty(User.prototype, "email", {
        get: function () {
            return this._email;
        },
        set: function (value) {
            this._email = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "id", {
        get: function () {
            return this._id;
        },
        set: function (value) {
            this._id = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "username", {
        get: function () {
            return this._username;
        },
        set: function (value) {
            this._username = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "age", {
        get: function () {
            return this._age;
        },
        set: function (value) {
            this._age = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "sex", {
        get: function () {
            return this._sex;
        },
        set: function (value) {
            this._sex = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "dateOfBirth", {
        get: function () {
            return this._dateOfBirth;
        },
        set: function (value) {
            this._dateOfBirth = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "activities", {
        get: function () {
            return this._activities;
        },
        set: function (value) {
            this._activities = value;
        },
        enumerable: true,
        configurable: true
    });
    return User;
}());



/***/ }),

/***/ "./src/app/nav-bar/nav-bar.component.css":
/*!***********************************************!*\
  !*** ./src/app/nav-bar/nav-bar.component.css ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/nav-bar/nav-bar.component.html":
/*!************************************************!*\
  !*** ./src/app/nav-bar/nav-bar.component.html ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\n  <ul class=\"nav navbar-nav navbar-right\">\n    <li class=\"nav-item\">\n      <a class=\"nav-link\" routerLink=\"/students\" routerLinkActive=\"active\">Studenci</a>\n    </li>\n    <li class=\"nav-item\">\n      <a class=\"nav-link\" routerLink=\"/activities\" routerLinkActive=\"active\">Zajęcia</a>\n    </li>\n    <li class=\"nav-item\">\n      <a class=\"nav-link\" routerLink=\"/login\" routerLinkActive=\"active\">Zaloguj się</a>\n    </li>\n    <li>\n      <a class=\"nav-link\" routerLink=\"/register\" routerLinkActive=\"active\">Zarejestruj się</a>\n    </li>\n  </ul>  \n</div>\n"

/***/ }),

/***/ "./src/app/nav-bar/nav-bar.component.ts":
/*!**********************************************!*\
  !*** ./src/app/nav-bar/nav-bar.component.ts ***!
  \**********************************************/
/*! exports provided: NavBarComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NavBarComponent", function() { return NavBarComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var NavBarComponent = /** @class */ (function () {
    function NavBarComponent() {
    }
    NavBarComponent.prototype.ngOnInit = function () {
    };
    NavBarComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-nav-bar',
            template: __webpack_require__(/*! ./nav-bar.component.html */ "./src/app/nav-bar/nav-bar.component.html"),
            styles: [__webpack_require__(/*! ./nav-bar.component.css */ "./src/app/nav-bar/nav-bar.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], NavBarComponent);
    return NavBarComponent;
}());



/***/ }),

/***/ "./src/app/new-activity/new-activity.component.css":
/*!*********************************************************!*\
  !*** ./src/app/new-activity/new-activity.component.css ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/new-activity/new-activity.component.html":
/*!**********************************************************!*\
  !*** ./src/app/new-activity/new-activity.component.html ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\n  new-activity works!\n</p>\n"

/***/ }),

/***/ "./src/app/new-activity/new-activity.component.ts":
/*!********************************************************!*\
  !*** ./src/app/new-activity/new-activity.component.ts ***!
  \********************************************************/
/*! exports provided: NewActivityComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NewActivityComponent", function() { return NewActivityComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var NewActivityComponent = /** @class */ (function () {
    function NewActivityComponent() {
    }
    NewActivityComponent.prototype.ngOnInit = function () {
    };
    NewActivityComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-new-activity',
            template: __webpack_require__(/*! ./new-activity.component.html */ "./src/app/new-activity/new-activity.component.html"),
            styles: [__webpack_require__(/*! ./new-activity.component.css */ "./src/app/new-activity/new-activity.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], NewActivityComponent);
    return NewActivityComponent;
}());



/***/ }),

/***/ "./src/app/new-student/new-student.component.css":
/*!*******************************************************!*\
  !*** ./src/app/new-student/new-student.component.css ***!
  \*******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/new-student/new-student.component.html":
/*!********************************************************!*\
  !*** ./src/app/new-student/new-student.component.html ***!
  \********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\n  new-student works!\n</p>\n"

/***/ }),

/***/ "./src/app/new-student/new-student.component.ts":
/*!******************************************************!*\
  !*** ./src/app/new-student/new-student.component.ts ***!
  \******************************************************/
/*! exports provided: NewStudentComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NewStudentComponent", function() { return NewStudentComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var NewStudentComponent = /** @class */ (function () {
    function NewStudentComponent() {
    }
    NewStudentComponent.prototype.ngOnInit = function () {
    };
    NewStudentComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-new-student',
            template: __webpack_require__(/*! ./new-student.component.html */ "./src/app/new-student/new-student.component.html"),
            styles: [__webpack_require__(/*! ./new-student.component.css */ "./src/app/new-student/new-student.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], NewStudentComponent);
    return NewStudentComponent;
}());



/***/ }),

/***/ "./src/app/register/register.component.css":
/*!*************************************************!*\
  !*** ./src/app/register/register.component.css ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "/* Credit to bootsnipp.com for the css for the color graph */\n.colorgraph {\n  height: 5px;\n  border-top: 0;\n  background: #c4e17f;\n  border-radius: 5px;\n  background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);\n}\n"

/***/ }),

/***/ "./src/app/register/register.component.html":
/*!**************************************************!*\
  !*** ./src/app/register/register.component.html ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!--<link href=\"//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">-->\n<!--<script src=\"//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js\"></script>-->\n<!--<script src=\"//code.jquery.com/jquery-1.11.1.min.js\"></script>-->\n<!------ Include the above in your HEAD tag ---------->\n\n  <div class=\"row\">\n    <div class=\"col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3\">\n        <h2>Please Sign Up <small>It's free and always will be.</small></h2>\n        <hr class=\"colorgraph\">\n        <div class=\"row\">\n          <div class=\"col-xs-12 col-sm-6 col-md-6\">\n            <div class=\"form-group\">\n              <input type=\"text\" name=\"first_name\" id=\"first_name\" class=\"form-control input-lg\" placeholder=\"First Name\" tabindex=\"1\" [(ngModel)]=\"firstName\">\n            </div>\n          </div>\n          <div class=\"col-xs-12 col-sm-6 col-md-6\">\n            <div class=\"form-group\">\n              <input type=\"text\" name=\"last_name\" id=\"last_name\" class=\"form-control input-lg\" placeholder=\"Last Name\" tabindex=\"2\" [(ngModel)]=\"lastName\">\n            </div>\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <input type=\"text\" name=\"display_name\" id=\"display_name\" class=\"form-control input-lg\" placeholder=\"Display Name\" tabindex=\"3\" [(ngModel)]=\"username\">\n        </div>\n        <div class=\"form-group\">\n          <input type=\"email\" name=\"email\" id=\"email\" class=\"form-control input-lg\" placeholder=\"Email Address\" tabindex=\"4\" [(ngModel)]=\"email\">\n        </div>\n        <div class=\"row\">\n          <div class=\"col-xs-12 col-sm-6 col-md-6\">\n            <div class=\"form-group\">\n              <input type=\"password\" name=\"password\" id=\"password\" class=\"form-control input-lg\" placeholder=\"Password\" tabindex=\"5\" [(ngModel)]=\"password\">\n            </div>\n          </div>\n          <div class=\"col-xs-12 col-sm-6 col-md-6\">\n            <div class=\"form-group\">\n              <input type=\"password\" name=\"password_confirmation\" id=\"password_confirmation\" class=\"form-control input-lg\" placeholder=\"Confirm Password\" tabindex=\"6\" [(ngModel)]=\"confirmPassword\">\n            </div>\n          </div>\n        </div>\n        <div class=\"row\">\n          <div class=\"col-xs-12 col-sm-12 col-md-12\">\n            By clicking <strong class=\"label label-primary\">Register</strong>, you agree to the <a href=\"#\" data-toggle=\"modal\" data-target=\"#t_and_c_m\">Terms and Conditions</a> set out by this site, including our Cookie Use.\n          </div>\n        </div>\n\n        <hr class=\"colorgraph\">\n        <div class=\"row\">\n          <div class=\"col-xs-12 col-md-6\"><button class=\"btn btn-primary btn-block btn-lg\" tabindex=\"7\" (click)=\"registerAccount()\">Zarejestruj się!</button></div>\n          <div class=\"col-xs-12 col-md-6\"><a href=\"/login\" class=\"btn btn-success btn-block btn-lg\">Sign In</a></div>\n        </div>\n    </div>\n  </div>\n  <!-- Modal -->\n  <div class=\"modal fade\" id=\"t_and_c_m\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n    <div class=\"modal-dialog modal-lg\">\n      <div class=\"modal-content\">\n        <div class=\"modal-header\">\n          <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×</button>\n          <h4 class=\"modal-title\" id=\"myModalLabel\">Terms & Conditions</h4>\n        </div>\n        <div class=\"modal-body\">\n          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>\n          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>\n          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>\n          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>\n          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>\n          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>\n          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Similique, itaque, modi, aliquam nostrum at sapiente consequuntur natus odio reiciendis perferendis rem nisi tempore possimus ipsa porro delectus quidem dolorem ad.</p>\n        </div>\n        <div class=\"modal-footer\">\n          <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\">I Agree</button>\n        </div>\n      </div><!-- /.modal-content -->\n    </div><!-- /.modal-dialog -->\n  </div><!-- /.modal -->\n"

/***/ }),

/***/ "./src/app/register/register.component.ts":
/*!************************************************!*\
  !*** ./src/app/register/register.component.ts ***!
  \************************************************/
/*! exports provided: RegisterComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegisterComponent", function() { return RegisterComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var src_app_services_register_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! src/app/services/register.service */ "./src/app/services/register.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var RegisterComponent = /** @class */ (function () {
    function RegisterComponent(registerService) {
        this.registerService = registerService;
    }
    RegisterComponent.prototype.ngOnInit = function () {
    };
    RegisterComponent.prototype.registerAccount = function () {
        var userDTO = {
            firstName: this.firstName,
            lastName: this.lastName,
            username: this.username,
            email: this.email,
            password: this.password,
            matchingPassword: this.confirmPassword
        };
        this.registerService.registerUser(userDTO)
            .subscribe(function (resp) {
            console.log('response from register: ', resp);
        }, function (error) {
            console.log(error);
        });
    };
    RegisterComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-register',
            template: __webpack_require__(/*! ./register.component.html */ "./src/app/register/register.component.html"),
            styles: [__webpack_require__(/*! ./register.component.css */ "./src/app/register/register.component.css")]
        }),
        __metadata("design:paramtypes", [src_app_services_register_service__WEBPACK_IMPORTED_MODULE_1__["RegisterService"]])
    ], RegisterComponent);
    return RegisterComponent;
}());



/***/ }),

/***/ "./src/app/services/activity.service.ts":
/*!**********************************************!*\
  !*** ./src/app/services/activity.service.ts ***!
  \**********************************************/
/*! exports provided: ActivityService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ActivityService", function() { return ActivityService; });
/* harmony import */ var _database_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./database.service */ "./src/app/services/database.service.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var ActivityService = /** @class */ (function (_super) {
    __extends(ActivityService, _super);
    function ActivityService() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.activityHref = '/activities';
        return _this;
    }
    ActivityService.prototype.getActivities = function () {
        return this.http.get(this.backendHref + this.activityHref, { headers: this.prepareHeaders() });
    };
    ActivityService.prototype.getActivityById = function (id) {
        return this.http.get(this.composeIdUrl(id), { headers: this.prepareHeaders() });
    };
    ActivityService.prototype.addActivity = function (activity) {
        return this.http.post(this.backendHref + this.activityHref, JSON.stringify({
            name: activity.name,
            location: activity.location,
            startDate: activity.startDate
        }), { headers: this.prepareHeaders() });
    };
    ActivityService.prototype.deleteActivity = function (id) {
        return this.http.delete(this.composeIdUrl(id));
    };
    ActivityService.prototype.updateActivity = function (id, activity) {
        return this.http.post(this.composeIdUrl(id), activity);
    };
    ActivityService.prototype.assignUser = function (activityId, userId) {
        return this.http.put(this.composeIdUrl(activityId) + '/users/' + userId.toString(), null);
    };
    ActivityService.prototype.composeIdUrl = function (id) {
        return this.backendHref + this.activityHref + '/' + id.toString();
    };
    return ActivityService;
}(_database_service__WEBPACK_IMPORTED_MODULE_0__["DatabaseService"]));



/***/ }),

/***/ "./src/app/services/database.service.ts":
/*!**********************************************!*\
  !*** ./src/app/services/database.service.ts ***!
  \**********************************************/
/*! exports provided: DatabaseService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DatabaseService", function() { return DatabaseService; });
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


/**
 * Created by kamil on 03.05.17.
 */
var DatabaseService = /** @class */ (function () {
    // protected backendHref = '/backend';
    function DatabaseService(http) {
        this.http = http;
        this.backendHref = 'http://localhost:' + '21851';
    }
    DatabaseService.prototype.get = function (endpoint, subscribe) {
        var url = this.backendHref + endpoint;
        this.http.get(url, { headers: this.prepareHeaders(), withCredentials: true, observe: 'response' })
            .subscribe(subscribe, this.handleError);
    };
    DatabaseService.prototype.post = function (endpoint, body, subscribe) {
        var url = this.backendHref + endpoint;
        this.http.post(url, body, { headers: this.prepareHeaders(), withCredentials: true, observe: 'response' })
            .subscribe(subscribe, this.handleError);
    };
    DatabaseService.prototype.put = function (endpoint, body, subscribe) {
        var url = this.backendHref + endpoint;
        this.http.put(url, body, { headers: this.prepareHeaders(), withCredentials: true, observe: 'response' })
            .subscribe(subscribe, this.handleError);
    };
    DatabaseService.prototype.delete = function (endpoint, body, subscribe) {
        var url = this.backendHref + endpoint;
        this.http.delete(url, { headers: this.prepareHeaders(), withCredentials: true, observe: 'response' })
            .subscribe(subscribe, this.handleError);
    };
    DatabaseService.prototype.handleError = function (error) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    };
    DatabaseService.prototype.prepareHeaders = function () {
        return new _angular_common_http__WEBPACK_IMPORTED_MODULE_0__["HttpHeaders"]()
            .append('Content-Type', 'application/json')
            .append('Accept', 'application/json');
    };
    DatabaseService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])(),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_0__["HttpClient"]])
    ], DatabaseService);
    return DatabaseService;
}());



/***/ }),

/***/ "./src/app/services/log-in.service.ts":
/*!********************************************!*\
  !*** ./src/app/services/log-in.service.ts ***!
  \********************************************/
/*! exports provided: LogInService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LogInService", function() { return LogInService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var ngx_cookie_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ngx-cookie-service */ "./node_modules/ngx-cookie-service/index.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var LogInService = /** @class */ (function () {
    function LogInService(http, cookie) {
        this.http = http;
        this.cookie = cookie;
        this._url = '/login';
        this.backendHref = 'http://localhost:' + '21851';
    }
    LogInService.prototype.login = function (username, password) {
        // const parameters: string = ['username=' + username, 'password=' + password].join('&');
        // const headers = new HttpHeaders().append('Content-Type', 'application/x-www-form-urlencoded');
        //
        // const result = this.http.post(this.backendHref + this._url, parameters, {headers: headers, observe: 'response'});
        // result.subscribe(response => this.mapResponse(response), (error) => {
        //   console.log(error);
        // });
        // return result;
        var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
            authorization: 'Basic ' + btoa(username + ':' + password)
        });
        return this.http.get(this.backendHref + this._url, { headers: headers });
    };
    LogInService.prototype.mapResponse = function (response) {
        console.log(response.headers);
    };
    LogInService.prototype.logout = function () {
        this.cookie.delete('JSESSIONID');
    };
    LogInService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"], ngx_cookie_service__WEBPACK_IMPORTED_MODULE_2__["CookieService"]])
    ], LogInService);
    return LogInService;
}());



/***/ }),

/***/ "./src/app/services/register.service.ts":
/*!**********************************************!*\
  !*** ./src/app/services/register.service.ts ***!
  \**********************************************/
/*! exports provided: RegisterService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegisterService", function() { return RegisterService; });
/* harmony import */ var _database_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./database.service */ "./src/app/services/database.service.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

// @Injectable({
//   providedIn: 'root'
// })
var RegisterService = /** @class */ (function (_super) {
    __extends(RegisterService, _super);
    function RegisterService() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.registerHref = '/register';
        return _this;
    }
    RegisterService.prototype.registerUser = function (userDTO) {
        return this.http.post(this.backendHref + this.registerHref, JSON.stringify(userDTO), { headers: this.prepareHeaders(), observe: 'response' });
    };
    return RegisterService;
}(_database_service__WEBPACK_IMPORTED_MODULE_0__["DatabaseService"]));



/***/ }),

/***/ "./src/app/services/user.service.ts":
/*!******************************************!*\
  !*** ./src/app/services/user.service.ts ***!
  \******************************************/
/*! exports provided: UserService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserService", function() { return UserService; });
/* harmony import */ var _database_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./database.service */ "./src/app/services/database.service.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var UserService = /** @class */ (function (_super) {
    __extends(UserService, _super);
    function UserService() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.usersHref = '/users';
        return _this;
    }
    UserService.prototype.getUsers = function () {
        return this.http.get(this.backendHref + this.usersHref);
    };
    UserService.prototype.getById = function (id) {
        return this.http.get(this.composeIdUrl(id));
    };
    UserService.prototype.addUser = function (user, callback) {
        return this.http.post(this.backendHref + this.usersHref, JSON.stringify({ username: user.username }), { headers: this.prepareHeaders() });
    };
    UserService.prototype.deleteUser = function (id, callback) {
        return this.http.delete(this.composeIdUrl(id), null);
    };
    UserService.prototype.updateUser = function (id, user) {
        return this.http.put(this.composeIdUrl(id), user);
    };
    UserService.prototype.assignActivity = function (activityId, userId) {
        return this.http.put(this.composeIdUrl(userId) + '/activities/' + activityId.toString(), null, { withCredentials: true });
    };
    UserService.prototype.composeIdUrl = function (id) {
        return this.backendHref + this.usersHref + '/' + id.toString();
    };
    return UserService;
}(_database_service__WEBPACK_IMPORTED_MODULE_0__["DatabaseService"]));



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * In development mode, for easier debugging, you can ignore zone related error
 * stack frames such as `zone.run`/`zoneDelegate.invokeTask` by importing the
 * below file. Don't forget to comment it out in production mode
 * because it will have a performance impact when errors are thrown
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! /home/kamil/Documents/IT/spring-HW/frontend/attendance/src/main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map