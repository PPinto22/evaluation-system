webpackJsonp([0,4],[
/* 0 */,
/* 1 */,
/* 2 */,
/* 3 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__models_user__ = __webpack_require__(14);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_app_services_http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_map__ = __webpack_require__(392);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_map__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AuthenticationService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var AuthenticationService = (function () {
    function AuthenticationService(router, http, httpUtil) {
        this.router = router;
        this.http = http;
        this.httpUtil = httpUtil;
        if (localStorage['currentUser'] !== undefined) {
            try {
                var userJson = JSON.parse(localStorage['currentUser']);
                this.userLogged = new __WEBPACK_IMPORTED_MODULE_1__models_user__["a" /* User */](userJson._id, userJson._email, userJson._firstName, userJson._lastName, userJson._type, userJson._token);
            }
            catch (error) {
                localStorage.removeItem('currentUser');
            }
        }
    }
    AuthenticationService.prototype.login = function (email, pass) {
        var _this = this;
        return this.http.post(this.httpUtil.url('/auth/login'), JSON.stringify({ email: email, password: pass }), this.httpUtil.headers('')).map(function (response) {
            var data = response.json();
            if (data && data.token && data.user) {
                var user = data.user;
                _this.userLogged = new __WEBPACK_IMPORTED_MODULE_1__models_user__["a" /* User */](user.id, user.email, user.firstName, user.lastName, user.type, data.token);
                localStorage.setItem('currentUser', JSON.stringify(_this.userLogged));
            }
        });
    };
    AuthenticationService.prototype.register = function (email, password, firstName, lastName, type) {
        return this.http.post(this.httpUtil.url('/auth/signup'), JSON.stringify({
            email: email,
            password: password,
            firstName: firstName,
            lastName: lastName,
            type: type
        }), this.httpUtil.headers(''))
            .map(function (response) {
            var data = response.json();
        });
    };
    AuthenticationService.prototype.logout = function () {
        this.userLogged = null;
        localStorage.removeItem('currentUser');
    };
    AuthenticationService.prototype.isLogged = function () {
        return this.userLogged != null ? true : false;
    };
    AuthenticationService.prototype.isTeacher = function () {
        return this.userLogged.isTeacher();
    };
    AuthenticationService.prototype.getUserId = function () {
        return this.userLogged.id;
    };
    AuthenticationService.prototype.isSudent = function () {
        return this.userLogged.isStudent();
    };
    AuthenticationService.prototype.getUserName = function () {
        return this.userLogged.firstName + ' ' + this.userLogged.lastName;
    };
    AuthenticationService.prototype.getToken = function () {
        return this.userLogged.token;
    };
    return AuthenticationService;
}());
AuthenticationService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_4__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__angular_http__["b" /* Http */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3_app_services_http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3_app_services_http_util_service__["a" /* HttpUtilService */]) === "function" && _c || Object])
], AuthenticationService);

var _a, _b, _c;
//# sourceMappingURL=authentication.service.js.map

/***/ }),
/* 4 */,
/* 5 */,
/* 6 */,
/* 7 */,
/* 8 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_Observable__ = __webpack_require__(6);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BreadCrumbService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var BreadCrumbService = (function () {
    function BreadCrumbService() {
        var _this = this;
        this.breadCrumDate = new __WEBPACK_IMPORTED_MODULE_1_rxjs_Observable__["Observable"](function (observer) {
            _this.nameBreadCrum = observer;
        });
    }
    BreadCrumbService.prototype.setBreadCrum = function (nameString) {
        this.nameBreadCrum.next(nameString);
    };
    return BreadCrumbService;
}());
BreadCrumbService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [])
], BreadCrumbService);

//# sourceMappingURL=breadcrumb.service.js.map

/***/ }),
/* 9 */,
/* 10 */,
/* 11 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__(6);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HttpUtilService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var HttpUtilService = (function () {
    function HttpUtilService() {
        // private API_URL = 'http://127.0.0.1:8080';
        // private API_URL = 'http://localhost:8080';
        this.API_URL = 'http://localhost:80';
    }
    HttpUtilService.prototype.url = function (path) {
        return this.API_URL + path;
    };
    HttpUtilService.prototype.headers = function (token) {
        var headersParams = {
            'Content-Type': 'application/json;charset=UTF-8'
            // 'Content-Type': 'application/x-www-form-urlencoded'
        };
        if (token) {
            headersParams['Authorization'] = 'Bearer ' + token;
        }
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* Headers */](headersParams);
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* RequestOptions */]({ headers: headers });
        return options;
    };
    HttpUtilService.prototype.extrairDados = function (response) {
        var data = response.json();
        return data || {};
    };
    HttpUtilService.prototype.processarErros = function (erro) {
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw('Erro acessando servidor remoto.');
    };
    return HttpUtilService;
}());
HttpUtilService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])()
], HttpUtilService);

//# sourceMappingURL=http-util.service.js.map

/***/ }),
/* 12 */,
/* 13 */,
/* 14 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return User; });
/**
 * Created by rjaf on 30/05/2017.
 */
var User = (function () {
    function User(id, email, firstName, lastName, type, token) {
        this._id = id;
        this._email = email;
        this._firstName = firstName;
        this._lastName = lastName;
        this._type = type;
        this._token = token;
    }
    Object.defineProperty(User.prototype, "active", {
        get: function () {
            return this._active;
        },
        set: function (value) {
            this._active = value;
        },
        enumerable: true,
        configurable: true
    });
    User.prototype.isTeacher = function () {
        return this._type === User._Teacher ? true : false;
    };
    User.prototype.isStudent = function () {
        return this._type === User._Student ? true : false;
    };
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
    Object.defineProperty(User.prototype, "type", {
        get: function () {
            return this._type;
        },
        set: function (value) {
            this._type = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "token", {
        get: function () {
            return this._token;
        },
        set: function (value) {
            this._token = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "firstName", {
        get: function () {
            return this._firstName;
        },
        set: function (value) {
            this._firstName = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "lastName", {
        get: function () {
            return this._lastName;
        },
        set: function (value) {
            this._lastName = value;
        },
        enumerable: true,
        configurable: true
    });
    User.prototype.getName = function () {
        return this.firstName + ' ' + this.lastName;
    };
    User.prototype.getNameActive = function () {
        return this.active ? this.firstName + ' ' + this.lastName + ' ( ' + this.email + ' )' : this.email;
    };
    return User;
}());

User._Teacher = 'Teacher';
User._Student = 'Student';
//# sourceMappingURL=user.js.map

/***/ }),
/* 15 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ExamsService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ExamsService = (function () {
    function ExamsService(http, httpUtil, authentication) {
        this.http = http;
        this.httpUtil = httpUtil;
        this.authentication = authentication;
    }
    // GET /api/groups/{group_id}/exams
    ExamsService.prototype.getExamsByGroupId = function (groupId) {
        return this.http.get(this.httpUtil.url('/api/groups/' + groupId + '/exams'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/users/{user_id}/exams
    ExamsService.prototype.getExamsByUserId = function (userId) {
        return this.http.get(this.httpUtil.url('/api/users/' + userId + '/exams'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/users/{user_id}/exams
    ExamsService.prototype.getExamsOnGoingByUserId = function (userId) {
        return this.http.get(this.httpUtil.url('/api/users/' + userId + '/exams?ongoing'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // POST /api/groups/{group_id}/exams
    ExamsService.prototype.createExamByGroupId = function (groupId, beginDate, duration, name, questionIds) {
        return this.http.post(this.httpUtil.url('/api/groups/' + groupId + '/exams'), JSON.stringify({
            beginDate: beginDate,
            duration: duration,
            name: name,
            questionIDs: questionIds
        }), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/exams/{exam_id}
    ExamsService.prototype.getExamById = function (examId) {
        return this.http.get(this.httpUtil.url('/api/exams/' + examId), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/users/{user_id}/submissions
    ExamsService.prototype.getSubmissionsByExamByGroupId = function (examId, groupId) {
        var headersParams = {
            'Content-Type': 'application/json;charset=UTF-8'
            // 'Content-Type': 'application/x-www-form-urlencoded'
        };
        if (this.authentication.getToken()) {
            headersParams['Authorization'] = 'Bearer ' + this.authentication.getToken();
        }
        var search = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* URLSearchParams */]();
        search.set('exam', '' + examId);
        search.set('group', '' + groupId);
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* Headers */](headersParams);
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* RequestOptions */]({ headers: headers, search: search });
        return this.http.get(this.httpUtil.url('/api/users/' + this.authentication.getUserId() + '/submissions'), options)
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/users/{user_id}/submissions?exam=exam_id
    ExamsService.prototype.getSubmissionsByExam = function (exam_id, user_id) {
        var headersParams = {
            'Content-Type': 'application/json;charset=UTF-8'
            // 'Content-Type': 'application/x-www-form-urlencoded'
        };
        if (this.authentication.getToken()) {
            headersParams['Authorization'] = 'Bearer ' + this.authentication.getToken();
        }
        var search = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* URLSearchParams */]();
        search.set('exam', '' + exam_id);
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* Headers */](headersParams);
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* RequestOptions */]({ headers: headers, search: search });
        return this.http.get(this.httpUtil.url('/api/users/' + user_id + '/submissions'), options)
            .map(this.httpUtil.extrairDados);
    };
    // POST /api/exams/{exam_id}/submissions
    ExamsService.prototype.createExameSubmission = function (examId) {
        return this.http.post(this.httpUtil.url('/api/exams/' + examId + '/submissions'), JSON.stringify({}), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/submissions/{submission_id}
    ExamsService.prototype.getBySubmission = function (submissionId) {
        return this.http.get(this.httpUtil.url('/api/submissions/' + submissionId), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // PUT /api/submissions/{submission_id}
    ExamsService.prototype.putExamAnswerSubmission = function (submissionId, questionId, answerId) {
        var json = {};
        json[questionId] = answerId;
        return this.http.put(this.httpUtil.url('/api/submissions/' + submissionId), JSON.stringify(json), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    return ExamsService;
}());
ExamsService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__authentication_service__["a" /* AuthenticationService */]) === "function" && _c || Object])
], ExamsService);

var _a, _b, _c;
//# sourceMappingURL=exams.service.js.map

/***/ }),
/* 16 */,
/* 17 */,
/* 18 */,
/* 19 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__(4);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Exception; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var Exception = (function () {
    function Exception(authentication, router) {
        this.authentication = authentication;
        this.router = router;
    }
    Exception.prototype.errorHandlingCreateClass = function (error) {
        var p = JSON.parse(error._body);
        if (p.message === 'Class already exists') {
            return true;
        }
        return false;
    };
    Exception.prototype.errorHandlingCreateGroup = function (error) {
        var p = JSON.parse(error._body);
        if (p.message === 'Group already exists') {
            return true;
        }
        return false;
    };
    Exception.prototype.errorHandlingInvalidToken = function (error) {
        if (error && error._body) {
            var error_body = JSON.parse(error._body);
            if (error.status === 401) {
                this.authentication.logout();
                this.router.navigate(['/login']);
            }
        }
    };
    return Exception;
}());
Exception = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */]) === "function" && _b || Object])
], Exception);

var _a, _b;
//# sourceMappingURL=exception.js.map

/***/ }),
/* 20 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Class; });
/**
 * Created by rjaf on 24/06/2017.
 */
var Class = (function () {
    function Class(name, abbreviation) {
        this._name = name;
        this._abbreviation = abbreviation;
    }
    Object.defineProperty(Class.prototype, "id", {
        get: function () {
            return this._id;
        },
        set: function (value) {
            this._id = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Class.prototype, "name", {
        get: function () {
            return this._name;
        },
        set: function (value) {
            this._name = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Class.prototype, "abbreviation", {
        get: function () {
            return this._abbreviation;
        },
        set: function (value) {
            this._abbreviation = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Class.prototype, "user", {
        get: function () {
            return this._user;
        },
        set: function (value) {
            this._user = value;
        },
        enumerable: true,
        configurable: true
    });
    return Class;
}());

//# sourceMappingURL=class.js.map

/***/ }),
/* 21 */,
/* 22 */,
/* 23 */,
/* 24 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Group; });
/**
 * Created by pedro on 26-06-2017.
 */
var Group = (function () {
    function Group(name) {
        this._name = name;
    }
    Object.defineProperty(Group.prototype, "id", {
        get: function () {
            return this._id;
        },
        set: function (value) {
            this._id = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Group.prototype, "name", {
        get: function () {
            return this._name;
        },
        set: function (value) {
            this._name = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Group.prototype, "class", {
        get: function () {
            return this._class;
        },
        set: function (value) {
            this._class = value;
        },
        enumerable: true,
        configurable: true
    });
    return Group;
}());

//# sourceMappingURL=group.js.map

/***/ }),
/* 25 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_Observable__ = __webpack_require__(6);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NavbarService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var NavbarService = (function () {
    function NavbarService() {
        var _this = this;
        this.navbarObservable = new __WEBPACK_IMPORTED_MODULE_1_rxjs_Observable__["Observable"](function (observer) {
            _this.navbarObserver = observer;
        });
    }
    NavbarService.prototype.sendUpdate = function (value) {
        this.navbarObserver.next(value);
    };
    return NavbarService;
}());
NavbarService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [])
], NavbarService);

//# sourceMappingURL=navbar.service.js.map

/***/ }),
/* 26 */,
/* 27 */,
/* 28 */,
/* 29 */,
/* 30 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Question; });
var Question = (function () {
    function Question(dificulty, category) {
        this.dificulty = dificulty;
        this.category = category;
    }
    return Question;
}());

Question._Hard = 3;
Question._Normal = 2;
Question._Easy = 1;
//# sourceMappingURL=question.js.map

/***/ }),
/* 31 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GroupService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var GroupService = (function () {
    function GroupService(router, http, httpUtil, authentication) {
        this.router = router;
        this.http = http;
        this.httpUtil = httpUtil;
        this.authentication = authentication;
    }
    // GET /api/groups/{group_id}
    GroupService.prototype.getGroupById = function (groupId) {
        return this.http.get(this.httpUtil.url('/api/groups/' + groupId), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/users/{user_id}/groups
    GroupService.prototype.getGroupByUser = function (userId) {
        return this.http.get(this.httpUtil.url('/api/users/' + userId + '/groups'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/classes/{class_id}/groups
    GroupService.prototype.getGroupByClass = function (classeId) {
        return this.http.get(this.httpUtil.url('/api/classes/' + classeId + '/groups'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/users/{user_id}/groups
    GroupService.prototype.getGroupsClassByUser = function (class_id, user_id) {
        var headersParams = {
            'Content-Type': 'application/json;charset=UTF-8'
            // 'Content-Type': 'application/x-www-form-urlencoded'
        };
        if (this.authentication.getToken()) {
            headersParams['Authorization'] = 'Bearer ' + this.authentication.getToken();
        }
        var search = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* URLSearchParams */]();
        search.set('_class', '' + class_id);
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* Headers */](headersParams);
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* RequestOptions */]({ headers: headers, search: search });
        return this.http.get(this.httpUtil.url('/api/users/' + user_id + '/groups'), options)
            .map(this.httpUtil.extrairDados);
    };
    // POST /api/classes/{class_id}/groups
    GroupService.prototype.createGroupByClasse = function (classeId, groupName) {
        return this.http.post(this.httpUtil.url('/api/classes/' + classeId + '/groups'), JSON.stringify({
            name: groupName
        }), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // DELETE /api/groups/{group_id}
    GroupService.prototype.deleteGroupById = function (group_id) {
        return this.http.delete(this.httpUtil.url('/api/groups/' + group_id), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    return GroupService;
}());
GroupService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */]) === "function" && _d || Object])
], GroupService);

var _a, _b, _c, _d;
//# sourceMappingURL=group.service.js.map

/***/ }),
/* 32 */,
/* 33 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Exam; });
/**
 * Created by pedro on 26-06-2017.
 */
var Exam = (function () {
    function Exam(name, beginDate, duration) {
        this._name = name;
        this._beginDate = new Date(beginDate);
        this._duration = duration;
    }
    Object.defineProperty(Exam.prototype, "id", {
        get: function () {
            return this._id;
        },
        set: function (value) {
            this._id = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Exam.prototype, "name", {
        get: function () {
            return this._name;
        },
        set: function (value) {
            this._name = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Exam.prototype, "beginDate", {
        get: function () {
            return this._beginDate;
        },
        set: function (value) {
            this._beginDate = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Exam.prototype, "duration", {
        get: function () {
            return this._duration;
        },
        set: function (value) {
            this._duration = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Exam.prototype, "group", {
        get: function () {
            return this._group;
        },
        set: function (value) {
            this._group = value;
        },
        enumerable: true,
        configurable: true
    });
    return Exam;
}());

//# sourceMappingURL=exam.js.map

/***/ }),
/* 34 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionsService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var QuestionsService = (function () {
    function QuestionsService(router, http, httpUtil, authentication) {
        this.router = router;
        this.http = http;
        this.httpUtil = httpUtil;
        this.authentication = authentication;
    }
    // GET /api/classes/{class_id}/questions
    QuestionsService.prototype.getAllQuestionsFromClasse = function (classeId) {
        return this.http.get(this.httpUtil.url('/api/classes/' + classeId + '/questions'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // POST /api/classes/{class_id}/questions TODO não tenhoa acerteza a parte das answers
    QuestionsService.prototype.createQuestionByClasse = function (classeId, questionName, categoryName, difficulty, answers) {
        return this.http.post(this.httpUtil.url('/api/classes/' + classeId + '/questions'), JSON.stringify({
            text: questionName,
            category: categoryName,
            difficulty: difficulty,
            answers: answers
        }), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/groups/{group_id}/questions/available
    QuestionsService.prototype.getAllQuestionsAvailableGroup = function (groupId) {
        return this.http.get(this.httpUtil.url('/api/groups/' + groupId + '/questions/available'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // POST /api/groups/{group_id}/exams/generate/question
    QuestionsService.prototype.createQuestionByGenerate = function (groupId, categoryName, difficulty, exclude) {
        return this.http.post(this.httpUtil.url('/api/groups/' + groupId + '/exams/generate/question'), JSON.stringify({
            category: categoryName,
            difficulty: difficulty,
            excluded: exclude
        }), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    return QuestionsService;
}());
QuestionsService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */]) === "function" && _d || Object])
], QuestionsService);

var _a, _b, _c, _d;
//# sourceMappingURL=questions.service.js.map

/***/ }),
/* 35 */,
/* 36 */,
/* 37 */,
/* 38 */,
/* 39 */,
/* 40 */,
/* 41 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ClassesService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ClassesService = (function () {
    function ClassesService(http, httpUtil, authentication) {
        this.http = http;
        this.httpUtil = httpUtil;
        this.authentication = authentication;
    }
    // GET /api/users/{user_id}/classes
    ClassesService.prototype.getAllClassesByUser = function (userId) {
        return this.http.get(this.httpUtil.url('/api/users/' + userId + '/classes'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/classes/{id}
    ClassesService.prototype.getById = function (classeId) {
        return this.http.get(this.httpUtil.url('/api/classes/' + classeId), this.httpUtil.headers(this.authentication.getToken())).map(this.httpUtil.extrairDados);
    };
    // POST /api/users/{user_id}/classes
    ClassesService.prototype.createClasseByUser = function (userId, classeName, abbreviation) {
        return this.http.post(this.httpUtil.url('/api/users/' + userId + '/classes'), JSON.stringify({
            name: classeName,
            abbreviation: abbreviation
        }), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // DELETE /api/classes/{class_id}
    ClassesService.prototype.deleteClass = function (classId) {
        return this.http.delete(this.httpUtil.url('/api/classes/' + classId), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    return ClassesService;
}());
ClassesService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__authentication_service__["a" /* AuthenticationService */]) === "function" && _c || Object])
], ClassesService);

var _a, _b, _c;
//# sourceMappingURL=classes.service.js.map

/***/ }),
/* 42 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ScoresService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var ScoresService = (function () {
    function ScoresService(router, http, httpUtil, authentication) {
        this.router = router;
        this.http = http;
        this.httpUtil = httpUtil;
        this.authentication = authentication;
    }
    // GET /api/users/{user_id}/scores
    ScoresService.prototype.getUserScore = function (userId) {
        return this.http.get(this.httpUtil.url('/api/users/' + userId + '/scores'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/exams/{exam_id}/scores
    ScoresService.prototype.getExamScore = function (exam_id) {
        return this.http.get(this.httpUtil.url('/api/exams/' + exam_id + '/scores'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/groups/{group_id}/scores
    ScoresService.prototype.getGroupScores = function (group_id) {
        return this.http.get(this.httpUtil.url('/api/groups/' + group_id + '/scores'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    return ScoresService;
}());
ScoresService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_http__["b" /* Http */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__http_util_service__["a" /* HttpUtilService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */]) === "function" && _d || Object])
], ScoresService);

var _a, _b, _c, _d;
//# sourceMappingURL=scores.service.js.map

/***/ }),
/* 43 */,
/* 44 */,
/* 45 */,
/* 46 */,
/* 47 */,
/* 48 */,
/* 49 */,
/* 50 */,
/* 51 */,
/* 52 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CategoriesService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var CategoriesService = (function () {
    function CategoriesService(router, http, httpUtil, authentication) {
        this.router = router;
        this.http = http;
        this.httpUtil = httpUtil;
        this.authentication = authentication;
    }
    // GET /api/classes/{class_id}/categories
    CategoriesService.prototype.getCategoriesByClasse = function (classeId) {
        return this.http.get(this.httpUtil.url('/api/classes/' + classeId + '/categories'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    return CategoriesService;
}());
CategoriesService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */]) === "function" && _d || Object])
], CategoriesService);

var _a, _b, _c, _d;
//# sourceMappingURL=categories.service.js.map

/***/ }),
/* 53 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var NotificationService = (function () {
    function NotificationService(router, http, httpUtil, authentication) {
        this.router = router;
        this.http = http;
        this.httpUtil = httpUtil;
        this.authentication = authentication;
    }
    // GET /api/users/{user_id}/notifications
    NotificationService.prototype.getUserNotification = function (userId) {
        return this.http.get(this.httpUtil.url('/api/users/' + userId + '/notifications'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/invitations/{invitation_id}/accept
    NotificationService.prototype.acceptNotification = function (invitation_id) {
        return this.http.post(this.httpUtil.url('/api/invitations/' + invitation_id + '/accept'), JSON.stringify({}), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/invitations/{invitation_id}/decline
    NotificationService.prototype.declineNotification = function (invitation_id) {
        return this.http.post(this.httpUtil.url('/api/invitations/' + invitation_id + '/decline'), JSON.stringify({}), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    return NotificationService;
}());
NotificationService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */]) === "function" && _d || Object])
], NotificationService);

var _a, _b, _c, _d;
//# sourceMappingURL=notification.service.js.map

/***/ }),
/* 54 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StudentsService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var StudentsService = (function () {
    function StudentsService(router, http, httpUtil, authentication) {
        this.router = router;
        this.http = http;
        this.httpUtil = httpUtil;
        this.authentication = authentication;
    }
    // GET /api/groups/{group_id}/students
    StudentsService.prototype.getUserByGroupId = function (groupId) {
        return this.http.get(this.httpUtil.url('/api/groups/' + groupId + '/students'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // POST /api/groups/{group_id}/students TODO nao tenho acerteza disto
    StudentsService.prototype.postStudentByGroup = function (groupId, allEmailsStudents) {
        return this.http.post(this.httpUtil.url('/api/groups/' + groupId + '/students'), JSON.stringify(allEmailsStudents), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // DELETE /api/groups/{group_id}/students/{student_id}
    StudentsService.prototype.deleteStudentById = function (groupId, studentId) {
        return this.http.delete(this.httpUtil.url('/api/groups/' + groupId + '/students/' + studentId), this.httpUtil.headers(this.authentication.getToken())).map(this.httpUtil.extrairDados);
    };
    return StudentsService;
}());
StudentsService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */]) === "function" && _d || Object])
], StudentsService);

var _a, _b, _c, _d;
//# sourceMappingURL=students.service.js.map

/***/ }),
/* 55 */,
/* 56 */,
/* 57 */,
/* 58 */,
/* 59 */,
/* 60 */,
/* 61 */,
/* 62 */,
/* 63 */,
/* 64 */,
/* 65 */,
/* 66 */,
/* 67 */,
/* 68 */,
/* 69 */,
/* 70 */,
/* 71 */,
/* 72 */,
/* 73 */,
/* 74 */,
/* 75 */,
/* 76 */,
/* 77 */,
/* 78 */,
/* 79 */,
/* 80 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_classes_service__ = __webpack_require__(41);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__models_class__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__models_user__ = __webpack_require__(14);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__execption_exception__ = __webpack_require__(19);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__services_navbar_service__ = __webpack_require__(25);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ClassComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};









var ClassComponent = (function () {
    function ClassComponent(authentication, breadCrumbService, route, _class, exception, router, navbar) {
        this.authentication = authentication;
        this.breadCrumbService = breadCrumbService;
        this.route = route;
        this._class = _class;
        this.exception = exception;
        this.router = router;
        this.navbar = navbar;
        this.classInformation = new __WEBPACK_IMPORTED_MODULE_5__models_class__["a" /* Class */]('', '');
        this.teacherClass = new __WEBPACK_IMPORTED_MODULE_6__models_user__["a" /* User */](-1, '', '', '', '', '');
    }
    ClassComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            _this.classId = +params['class_id'];
            _this.getClassInformation();
        });
    };
    ClassComponent.prototype.ngAfterViewInit = function () {
        x_navigation();
        page_content_onresize();
    };
    ClassComponent.prototype.setBreadCrumb = function () {
        this.breadCrumbService.setBreadCrum(['Classes', this.classInformation.name]);
    };
    ClassComponent.prototype.isTeacher = function () {
        return this.authentication.isTeacher();
    };
    ClassComponent.prototype.getClassInformation = function () {
        var _this = this;
        this._class.getById(this.classId).subscribe(function (resultado) {
            _this.createClass(resultado);
            _this.setBreadCrumb();
        }, function (error) {
            _this.exception.errorHandlingInvalidToken(error);
            console.log(error);
        });
    };
    ClassComponent.prototype.createClass = function (resultado) {
        var teacher = resultado.teacher;
        this.teacherClass = new __WEBPACK_IMPORTED_MODULE_6__models_user__["a" /* User */](teacher.id, teacher.email, teacher.firstName, teacher.lastName, teacher.type, '');
        var _classe = new __WEBPACK_IMPORTED_MODULE_5__models_class__["a" /* Class */](resultado.name, resultado.abbreviation);
        this.classInformation = _classe;
    };
    // FIXME popup
    // clickRemoveClass(): void {
    //   this.popup2.options = {
    //     header: "" ,
    //     color: "red", // red, blue....
    //     widthProsentage: 40, // The with of the popou measured by browser width
    //     animationDuration: 1, // in seconds, 0 = no animation
    //     showButtons: true, // You can hide this in case you want to use custom buttons
    //     confirmBtnContent: "OK", // The text on your confirm button
    //     cancleBtnContent: "Cancel", // the text on your cancel button
    //     confirmBtnClass: "btn btn-default", // your class for styling the confirm button
    //     cancleBtnClass: "btn btn-default", // you class for styling the cancel button
    //     animation: "fadeInDown" // 'fadeInLeft', 'fadeInRight', 'fadeInUp', 'bounceIn','bounceInDown'
    //   };
    //
    //   this.popup2.show(this.popup2.options);
    // }
    ClassComponent.prototype.cancelDeleteClass = function () {
    };
    ClassComponent.prototype.confirmDeleteClass = function () {
        this.removeClass();
    };
    ClassComponent.prototype.removeClass = function () {
        var _this = this;
        this._class.deleteClass(this.classId).subscribe(function (result) {
            _this.navbar.sendUpdate(true);
            _this.router.navigate(['/dashboard']);
        }, function (error) {
            console.log(error);
        });
    };
    return ClassComponent;
}());
ClassComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-class',
        template: __webpack_require__(359),
        styles: [__webpack_require__(328)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["c" /* ActivatedRoute */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__services_classes_service__["a" /* ClassesService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_classes_service__["a" /* ClassesService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_7__execption_exception__["a" /* Exception */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_7__execption_exception__["a" /* Exception */]) === "function" && _e || Object, typeof (_f = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */]) === "function" && _f || Object, typeof (_g = typeof __WEBPACK_IMPORTED_MODULE_8__services_navbar_service__["a" /* NavbarService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_8__services_navbar_service__["a" /* NavbarService */]) === "function" && _g || Object])
], ClassComponent);

var _a, _b, _c, _d, _e, _f, _g;
//# sourceMappingURL=class.component.js.map

/***/ }),
/* 81 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__models_question__ = __webpack_require__(30);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_questions_service__ = __webpack_require__(34);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_exams_service__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__angular_common__ = __webpack_require__(29);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ExamCreateComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var ExamCreateComponent = (function () {
    function ExamCreateComponent(question, route, exam, breadCrumb, location) {
        var _this = this;
        this.question = question;
        this.route = route;
        this.exam = exam;
        this.breadCrumb = breadCrumb;
        this.location = location;
        this.examCreate = {};
        this.allQuestionsAvailable = {};
        this.updateDificulty = false;
        this.saveAll = false;
        this.generateAll = false;
        this.invalidDate = false;
        this.route.params.subscribe(function (params) {
            _this.groupId = +params['group_id'];
        });
        this.questionsIds = [];
        this.categoriesName = [];
        this.allGenerateNow = new Map();
    }
    ExamCreateComponent.prototype.ngOnInit = function () {
        this.questions = [];
        this.number_questions = 1;
        this.examCreate.questionNumber = 1;
        this.questions.push(new __WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */](__WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */]._Normal, ''));
        this.getAllQuestionsAvailable();
        this.breadCrumb.setBreadCrum(['Class > Group > Exam > New']);
    };
    ExamCreateComponent.prototype.focusOutQuestionNumber = function () {
        if (this.examCreate.questionNumber > this.number_questions) {
            var addQuestions = this.examCreate.questionNumber - this.number_questions;
            while (addQuestions > 0) {
                addQuestions--;
                this.addQuestion();
            }
            this.number_questions = this.examCreate.questionNumber;
        }
    };
    ExamCreateComponent.prototype.deleteQuestion = function (question) {
        this.questions.filter(function (question_item) {
            return question_item !== question;
        });
    };
    ExamCreateComponent.prototype.addQuestion = function () {
        this.questions.push(new __WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */](__WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */]._Normal, ''));
        this.number_questions++;
        this.examCreate.questionNumber = this.number_questions;
        page_content_onresize();
    };
    ExamCreateComponent.prototype.createExam = function () {
        var _this = this;
        this.invalidDate = false;
        // if ( this.examCreate.dateExam ) {
        // const dateNow = new Date();
        // const dateExameChoise = new Date(this.examCreate.dateExam);
        // if (dateNow < dateExameChoise) {
        if (this.examCreate.nameExam && this.examCreate.questionNumber && this.examCreate.duration && this.examCreate.dateExam && this.examCreate.hourExam) {
            if (this.examCreate.duration > 0) {
                // const hour = this.examCreate.hourExam[0] + '' + this.examCreate.hourExam[1];
                // const minu = this.examCreate.hourExam[3] + '' + this.examCreate.hourExam[4];
                // dateExameChoise.setMinutes( +minu);
                // dateExameChoise.setHours( +hour);
                var dateExameChoise = new Date();
                // FIXME alterar isto
                if (this.validateAllQuestions()) {
                    this.exam.createExamByGroupId(this.groupId, dateExameChoise.getTime(), this.examCreate.duration, this.examCreate.nameExam, this.questionsIds).subscribe(function (resultado) {
                        _this.location.back();
                    }, function (error) {
                        console.log(error);
                    });
                }
            }
            // }
            // } else {
            //  this.invalidDate = true;
            // TODO avisar data errada
            // }
        }
    };
    ExamCreateComponent.prototype.validateAllQuestions = function () {
        var allok = true;
        this.questionsIds = [];
        for (var _i = 0, _a = this.questions; _i < _a.length; _i++) {
            var quest = _a[_i];
            if (!quest.id) {
                allok = false;
                break;
            }
            this.questionsIds.push(quest.id);
        }
        return allok;
    };
    ExamCreateComponent.prototype.validateAll = function () {
        var p = new Date(this.examCreate.dateExam);
        var hours = this.examCreate.hourExam[0] + this.examCreate.hourExam[1];
        var minutes = this.examCreate.hourExam[3] + this.examCreate.hourExam[4];
        p.setMinutes(+minutes);
        p.setHours(+hours);
        return p;
    };
    ExamCreateComponent.prototype.removeQuest = function ($event) {
        if (this.number_questions > 1) {
            var indexQuestion = this.questions.indexOf($event);
            this.questions.splice(indexQuestion, 1);
            this.number_questions--;
            if (this.examCreate.questionNumber > 1) {
                this.examCreate.questionNumber--;
            }
        }
    };
    ExamCreateComponent.prototype.getAllQuestionsAvailable = function () {
        var _this = this;
        this.question.getAllQuestionsAvailableGroup(this.groupId).subscribe(function (resultado) {
            _this.allQuestionsAvailable = resultado;
            for (var nameCategory in resultado) {
                _this.categoriesName.push(nameCategory);
            }
        }, function (error) {
            console.log(error);
        });
    };
    ExamCreateComponent.prototype.removeFromAllQuestionsAvailable = function ($event) {
        var _this = this;
        setTimeout(function () {
            var quest = _this.questions.find(function (x) { return x.id === $event; });
            if (_this.allGenerateNow.get(quest.category)) {
                var categoryexist = _this.allGenerateNow.get(quest.category);
                if (categoryexist.get(+quest.dificulty)) {
                    if (!_this.allGenerateNow.get(quest.category).get(+quest.dificulty).find(function (x) { return x === quest.id; })) {
                        _this.allGenerateNow.get(quest.category).get(+quest.dificulty).push(quest.id);
                    }
                }
                else {
                    var newArray = new Array();
                    newArray.push(quest.id);
                    _this.allGenerateNow.get(quest.category).set(+quest.dificulty, newArray);
                }
            }
            else {
                var newArray = new Array();
                newArray.push(quest.id);
                var newMap = new Map();
                newMap.set(+quest.dificulty, newArray);
                _this.allGenerateNow.set(quest.category, newMap);
            }
            if (_this.updateDificulty) {
                _this.updateDificulty = false;
            }
            else {
                _this.updateDificulty = true;
            }
        }, 1);
    };
    ExamCreateComponent.prototype.changeNow = function () {
        if (this.updateDificulty) {
            this.updateDificulty = false;
        }
        else {
            this.updateDificulty = true;
        }
    };
    ExamCreateComponent.prototype.generateAllNow = function () {
        var _this = this;
        this.saveAll = !this.saveAll;
        var allok = true;
        setTimeout(function () {
            for (var _i = 0, _a = _this.questions; _i < _a.length; _i++) {
                var quest = _a[_i];
                if (((quest.dificulty + '') !== '-1' && quest.dificulty) && quest.category) {
                    // FIXME OLHA COMO etá isto é necessário
                    // console.log('entrei  aqui');
                }
                else {
                    // console.log(' lixo com esta');
                    allok = false;
                    break;
                }
            }
            if (allok) {
                _this.generateAll = true;
            }
        }, 1);
    };
    return ExamCreateComponent;
}());
ExamCreateComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-exam-create',
        template: __webpack_require__(360),
        styles: [__webpack_require__(329)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__services_questions_service__["a" /* QuestionsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_questions_service__["a" /* QuestionsService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* ActivatedRoute */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_4__services_exams_service__["a" /* ExamsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_exams_service__["a" /* ExamsService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_5__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_6__angular_common__["Location"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__angular_common__["Location"]) === "function" && _e || Object])
], ExamCreateComponent);

var _a, _b, _c, _d, _e;
//# sourceMappingURL=exam-create.component.js.map

/***/ }),
/* 82 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__models_submission__ = __webpack_require__(102);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_scores_service__ = __webpack_require__(42);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__models_user__ = __webpack_require__(14);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__angular_router__ = __webpack_require__(4);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ExamResultAllComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var ExamResultAllComponent = (function () {
    function ExamResultAllComponent(route, authentication, breadCrumbService, scoresService) {
        this.route = route;
        this.authentication = authentication;
        this.breadCrumbService = breadCrumbService;
        this.scoresService = scoresService;
        this.model = {};
    }
    ExamResultAllComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            _this.class_id = +params['class_id'];
            _this.group_id = +params['group_id'];
            _this.exam_id = +params['exam_id'];
            _this.getExamResults(_this.exam_id);
        });
        this.breadCrumbService.setBreadCrum(['Class > Group > Exam > Result']);
    };
    ExamResultAllComponent.prototype.getExamResults = function (exam_id) {
        var _this = this;
        this.scoresService.getExamScore(exam_id).subscribe(function (result) {
            _this.submissionResults = [];
            for (var _i = 0, _a = result.students; _i < _a.length; _i++) {
                var student = _a[_i];
                var new_submission = _this.createSubmission(student.score);
                new_submission.user = _this.createUser(student.student);
                _this.submissionResults.push(new_submission);
            }
        }, function (error) {
            console.log(error);
        });
    };
    ExamResultAllComponent.prototype.createUser = function (user) {
        var new_user = new __WEBPACK_IMPORTED_MODULE_5__models_user__["a" /* User */](user.id, user.email, user.firstName, user.lastName, user.type, '');
        new_user.active = user.active;
        return new_user;
    };
    ExamResultAllComponent.prototype.createSubmission = function (score) {
        var new_submission = new __WEBPACK_IMPORTED_MODULE_1__models_submission__["a" /* Submission */](score.submissionID, score.score);
        new_submission.correct = score.correct;
        new_submission.total = score.total;
        return new_submission;
    };
    ExamResultAllComponent.prototype.refreshExamResult = function () {
        this.getExamResults(this.exam_id);
    };
    return ExamResultAllComponent;
}());
ExamResultAllComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-exam-result-all',
        template: __webpack_require__(363),
        styles: [__webpack_require__(332)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_6__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__angular_router__["c" /* ActivatedRoute */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__services_scores_service__["a" /* ScoresService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_scores_service__["a" /* ScoresService */]) === "function" && _d || Object])
], ExamResultAllComponent);

var _a, _b, _c, _d;
//# sourceMappingURL=exam-result-all.component.js.map

/***/ }),
/* 83 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__(29);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_exams_service__ = __webpack_require__(15);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ExamResultComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ExamResultComponent = (function () {
    function ExamResultComponent(location, router, examsService) {
        this.location = location;
        this.router = router;
        this.examsService = examsService;
        this.exam = {};
        this.nameExam = '';
    }
    ExamResultComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.choiseanswersId = [];
        this.router.params.subscribe(function (params) {
            _this.submissionId = +params['submission_id'];
        });
        this.getExamSubmission();
    };
    ExamResultComponent.prototype.getExamSubmission = function () {
        var _this = this;
        this.examsService.getBySubmission(this.submissionId).subscribe(function (result) {
            _this.exam = result;
            _this.scoreExam = result.score;
            _this.nameExam = result.exam.name;
            _this.getAllQuestions(_this.exam.questions);
        }, function (error) {
            console.log(error);
        });
    };
    ExamResultComponent.prototype.getAllQuestions = function (questionsAll) {
        this.questions = [];
        for (var _i = 0, questionsAll_1 = questionsAll; _i < questionsAll_1.length; _i++) {
            var quest = questionsAll_1[_i];
            if (quest.answer) {
                this.choiseanswersId.push(quest.answer.id);
            }
            else {
                this.choiseanswersId.push(-1);
            }
            // if ( ques.question. quest.question.answers.find( x => x.correct === true).id )
            quest.question.dificulty = quest.question.difficulty;
            this.questions.push(quest.question);
        }
    };
    ExamResultComponent.prototype.goBack = function () {
        this.location.back();
    };
    return ExamResultComponent;
}());
ExamResultComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-exam-result',
        template: __webpack_require__(364),
        styles: [__webpack_require__(333)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_common__["Location"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_common__["Location"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* ActivatedRoute */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__services_exams_service__["a" /* ExamsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_exams_service__["a" /* ExamsService */]) === "function" && _c || Object])
], ExamResultComponent);

var _a, _b, _c;
//# sourceMappingURL=exam-result.component.js.map

/***/ }),
/* 84 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__models_question__ = __webpack_require__(30);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_exams_service__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ExamSubmissionComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var ExamSubmissionComponent = (function () {
    function ExamSubmissionComponent(breadCrumb, examsService, route, router) {
        this.breadCrumb = breadCrumb;
        this.examsService = examsService;
        this.route = route;
        this.router = router;
        this.exam = {};
    }
    ExamSubmissionComponent.prototype.ngOnInit = function () {
        var _this = this;
        // para teste
        this.saveAll = false;
        this.choiseanswersId = [];
        this.questions = [];
        var question1 = new __WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */](1, 'spring');
        question1.id = 1;
        var question2 = new __WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */](2, 'angular');
        question2.id = 1;
        this.questions.push(question1);
        this.questions.push(question2);
        this.route.params.subscribe(function (params) {
            _this.examId = +params['exam_id'];
            _this.groupId = +params['group_id'];
            _this.getExam(_this.examId);
            _this.breadCrumb.setBreadCrum(['Exam OnGoing']); // FIXME alterações
        });
    };
    ExamSubmissionComponent.prototype.getExam = function (exam_id) {
        var _this = this;
        this.examsService.getSubmissionsByExamByGroupId(this.examId, this.groupId).subscribe(function (resultado) {
            if (resultado.length === 0) {
                _this.createExamSubmission();
            }
            else {
                _this.submissionId = resultado[0].id;
                _this.getExamSubmission();
            }
        }, function (error) {
            console.log(error);
        });
    };
    ExamSubmissionComponent.prototype.getExamSubmission = function () {
        var _this = this;
        this.examsService.getBySubmission(this.submissionId).subscribe(function (result) {
            _this.exam = result;
            _this.nameExam = result.exam.name;
            _this.getAllQuestions(_this.exam.questions);
        }, function (error) {
            console.log(error);
        });
    };
    ExamSubmissionComponent.prototype.createExamSubmission = function () {
        var _this = this;
        this.examsService.createExameSubmission(this.examId).subscribe(function (result) {
            _this.submissionId = result.id;
            _this.exam = result;
            // FIXME TER CUIDADO POIS NAO TENHO ACERTEZA DISTO!
            _this.getExam(_this.examId);
        }, function (error) {
            console.log(error);
        });
    };
    ExamSubmissionComponent.prototype.getAllQuestions = function (questionsAll) {
        this.questions = [];
        for (var _i = 0, questionsAll_1 = questionsAll; _i < questionsAll_1.length; _i++) {
            var quest = questionsAll_1[_i];
            if (quest.answer) {
                this.choiseanswersId.push(quest.answer.id);
            }
            else {
                this.choiseanswersId.push(-1);
            }
            this.questions.push(quest.question);
        }
    };
    ExamSubmissionComponent.prototype.saveEverything = function () {
        this.saveAll = !this.saveAll;
    };
    return ExamSubmissionComponent;
}());
ExamSubmissionComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-exam-submission',
        template: __webpack_require__(365),
        styles: [__webpack_require__(334)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_exams_service__["a" /* ExamsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_exams_service__["a" /* ExamsService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["c" /* ActivatedRoute */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */]) === "function" && _d || Object])
], ExamSubmissionComponent);

var _a, _b, _c, _d;
//# sourceMappingURL=exam-submission.component.js.map

/***/ }),
/* 85 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_exams_service__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__models_exam__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_group_service__ = __webpack_require__(31);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__models_group__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__models_class__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__models_user__ = __webpack_require__(14);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ExamsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};










var ExamsComponent = (function () {
    function ExamsComponent(router, route, examsServices, groupsServices, authentication, breadcrumbService) {
        this.router = router;
        this.route = route;
        this.examsServices = examsServices;
        this.groupsServices = groupsServices;
        this.authentication = authentication;
        this.breadcrumbService = breadcrumbService;
        this.questions = [];
    }
    ExamsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.exam = new __WEBPACK_IMPORTED_MODULE_3__models_exam__["a" /* Exam */]('', new Date().getMilliseconds(), 120);
        this.route.params.subscribe(function (params) {
            _this.examId = +params['exam_id'];
            _this.exam.id = +params['exam_id'];
            _this.groupId = +params['group_id'];
            if (_this.isTeacher()) {
                _this.getExamById(_this.examId);
            }
            _this.getGroupById(_this.groupId);
        });
    };
    ExamsComponent.prototype.setBreadCrumb = function () {
        if (this.exam.group && this.exam.group.class && this.exam.group.class.user) {
            this.breadcrumbService.setBreadCrum([
                this.exam.group.class.name,
                this.exam.group.name,
                this.exam.name
            ]);
        }
    };
    ExamsComponent.prototype.getExamById = function (examId) {
        var _this = this;
        this.examsServices.getExamById(examId).subscribe(function (result) {
            _this.exam.name = result.name;
            _this.exam.beginDate = result.beginDate;
            _this.exam.duration = result.duration;
            for (var _i = 0, _a = result.questions; _i < _a.length; _i++) {
                var question_res = _a[_i];
                _this.questions.push(question_res);
            }
        }, function (error) {
            console.log(error);
        });
    };
    ExamsComponent.prototype.getGroupById = function (groupId) {
        var _this = this;
        this.groupsServices.getGroupById(groupId).subscribe(function (result) {
            _this.exam.group = new __WEBPACK_IMPORTED_MODULE_6__models_group__["a" /* Group */](result.name);
            _this.exam.group.id = result.id;
            _this.exam.group.class = new __WEBPACK_IMPORTED_MODULE_7__models_class__["a" /* Class */](result._class.name, result._class.abbreviation);
            _this.exam.group.class.id = result._class.id;
            _this.exam.group.class.user = new __WEBPACK_IMPORTED_MODULE_8__models_user__["a" /* User */](result._class.teacher.id, result._class.teacher.email, result._class.teacher.firstName, result._class.teacher.lastName, result._class.teacher.type, '');
            _this.exam.group.class.user.active = result._class.teacher.type;
            _this.setBreadCrumb();
        }, function (error) {
            console.log(error);
        });
    };
    ExamsComponent.prototype.timeToMakeExam = function () {
        // TODO testar se a data está entre o inicio e a soma da duração para mandalo para a pagina de fazer o exame
    };
    ExamsComponent.prototype.isTeacher = function () {
        return this.authentication.isTeacher();
    };
    ExamsComponent.prototype.getClassName = function () {
        return this.exam.group && this.exam.group.class && this.exam.group.class.name ? this.exam.group.class.name : '';
    };
    ExamsComponent.prototype.getClassAbbreviation = function () {
        return this.exam.group && this.exam.group.class && this.exam.group.class.abbreviation ? this.exam.group.class.abbreviation : '';
    };
    ExamsComponent.prototype.getGroupName = function () {
        return this.exam.group && this.exam.group && this.exam.group.name ? this.exam.group.name : '';
    };
    ExamsComponent.prototype.getTeacher = function () {
        return this.exam.group ? this.exam.group.class.user.firstName + '' + this.exam.group.class.user.lastName + '(' + this.exam.group.class.user.email + ')' : '';
    };
    return ExamsComponent;
}());
ExamsComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-exams',
        template: __webpack_require__(367),
        styles: [__webpack_require__(336)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* ActivatedRoute */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__services_exams_service__["a" /* ExamsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_exams_service__["a" /* ExamsService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__services_group_service__["a" /* GroupService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_group_service__["a" /* GroupService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_5__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _e || Object, typeof (_f = typeof __WEBPACK_IMPORTED_MODULE_9__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_9__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _f || Object])
], ExamsComponent);

var _a, _b, _c, _d, _e, _f;
//# sourceMappingURL=exams.component.js.map

/***/ }),
/* 86 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_group_service__ = __webpack_require__(31);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_students_service__ = __webpack_require__(54);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__execption_exception__ = __webpack_require__(19);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__services_navbar_service__ = __webpack_require__(25);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GroupCreateComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var GroupCreateComponent = (function () {
    function GroupCreateComponent(group, router, route, students, exception, breadCrumb, navbarService) {
        var _this = this;
        this.group = group;
        this.router = router;
        this.route = route;
        this.students = students;
        this.exception = exception;
        this.breadCrumb = breadCrumb;
        this.navbarService = navbarService;
        this.groupCreate = {};
        this.wrongEmail = '';
        this.groupAlreadyExists = false;
        this.model = {};
        this.route.parent.params.subscribe(function (params) {
            _this.classId = +params['class_id'];
        });
        this.allStudentsOfGroup = [];
        this.breadCrumb.setBreadCrum(['Class > Group > New']);
    }
    GroupCreateComponent.prototype.ngOnInit = function () {
        // this.cleanAll(); limpar tudo depois talvez
        // TODO talvez seja preciso mudar isto, depende como ficar, caso ele selecione um grupo é preciso colocar a verdadeiro.
        this.haveCreateGroup = false;
        this.groupAlreadyExists = false;
        // this.groupId = 3;
        this.nameGroupValid = false;
    };
    GroupCreateComponent.prototype.ngAfterViewInit = function () {
        x_navigation();
        page_content_onresize();
        this.scroll();
    };
    GroupCreateComponent.prototype.scroll = function () {
        $('.scroll').mCustomScrollbar({
            axis: 'y',
            autoHideScrollbar: true,
            scrollInertia: 20,
            advanced: {
                autoScrollOnFocus: false
            }
        }, { passive: true });
    };
    GroupCreateComponent.prototype.saveGroup = function () {
        if (this.groupCreate.nameGroup) {
            this.nameGroupValid = false;
            this.createGroup();
        }
        else {
            this.nameGroupValid = true;
        }
    };
    GroupCreateComponent.prototype.createGroup = function () {
        var _this = this;
        this.group.createGroupByClasse(this.classId, this.groupCreate.nameGroup).subscribe(function (resultado) {
            _this.haveCreateGroup = true;
            _this.groupId = resultado.id;
            _this.navbarService.sendUpdate(true);
            _this.addStudents();
        }, function (error) {
            if (error.status === 406) {
                _this.groupAlreadyExists = _this.exception.errorHandlingCreateGroup(error);
            }
            console.log(error);
        });
    };
    GroupCreateComponent.prototype.addStudents = function () {
        var _this = this;
        this.students.postStudentByGroup(this.groupId, this.allStudentsOfGroup).subscribe(function (resultado) {
            _this.router.navigate(['/dashboard', 'classes', _this.classId, 'groups', _this.groupId]);
        }, function (error) {
            console.log(error);
            _this.router.navigate(['/dashboard', 'classes', _this.classId, 'groups', _this.groupId]);
        });
    };
    GroupCreateComponent.prototype.treatmentEmail = function () {
        if (this.groupCreate.students) {
            this.wrongEmail = '';
            var separators = [',', ';', '\n'];
            var allNewStudents = [];
            for (var _i = 0, _a = this.groupCreate.students.toString().split(new RegExp(separators.join('|'))); _i < _a.length; _i++) {
                var email = _a[_i];
                if (!this.validateEmail(email)) {
                    if (this.wrongEmail === '') {
                        this.wrongEmail = email;
                    }
                    else {
                        this.wrongEmail = this.wrongEmail + '\n' + email;
                    }
                }
                else {
                    this.allStudentsOfGroup.push(email);
                }
            }
            this.groupCreate.students = this.wrongEmail;
        }
    };
    GroupCreateComponent.prototype.removeStudent = function (student) {
        var idStudent = this.allStudentsOfGroup.indexOf(student);
        this.allStudentsOfGroup.splice(idStudent, 1);
    };
    GroupCreateComponent.prototype.validateEmail = function (email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    };
    return GroupCreateComponent;
}());
GroupCreateComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-group-create',
        template: __webpack_require__(368),
        styles: [__webpack_require__(337)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__services_group_service__["a" /* GroupService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_group_service__["a" /* GroupService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* ActivatedRoute */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_3__services_students_service__["a" /* StudentsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_students_service__["a" /* StudentsService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_4__execption_exception__["a" /* Exception */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__execption_exception__["a" /* Exception */]) === "function" && _e || Object, typeof (_f = typeof __WEBPACK_IMPORTED_MODULE_5__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _f || Object, typeof (_g = typeof __WEBPACK_IMPORTED_MODULE_6__services_navbar_service__["a" /* NavbarService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__services_navbar_service__["a" /* NavbarService */]) === "function" && _g || Object])
], GroupCreateComponent);

var _a, _b, _c, _d, _e, _f, _g;
//# sourceMappingURL=group-create.component.js.map

/***/ }),
/* 87 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_angular2_csv__ = __webpack_require__(271);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_angular2_csv___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_angular2_csv__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_scores_service__ = __webpack_require__(42);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GroupScoreComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var GroupScoreComponent = (function () {
    function GroupScoreComponent(scoresService, breadCrumbService, route) {
        this.scoresService = scoresService;
        this.breadCrumbService = breadCrumbService;
        this.route = route;
        this.model = {};
    }
    GroupScoreComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.header = ['Name', 'Email'];
        this.studentsScores = [];
        this.route.params.subscribe(function (params) {
            _this.class_id = +params['class_id'];
            _this.group_id = +params['group_id'];
            _this.getGroupScores(_this.group_id);
            _this.setBreadCrumb();
        });
    };
    GroupScoreComponent.prototype.setBreadCrumb = function () {
        // FIXME alterar para a forma dinamica
        this.breadCrumbService.setBreadCrum(['Classes > Arquiteturas Aplicacionais > Results']);
    };
    GroupScoreComponent.prototype.exportResults = function () {
        var export_scores = [];
        export_scores.push(this.header);
        for (var _i = 0, _a = this.studentsScores; _i < _a.length; _i++) {
            var score = _a[_i];
            var line = [];
            line.push(score.name);
            line.push(score.email);
            for (var _b = 0, _c = score.exams; _b < _c.length; _b++) {
                var exam = _c[_b];
                line.push(exam);
            }
            export_scores.push(line);
        }
        new __WEBPACK_IMPORTED_MODULE_1_angular2_csv__["Angular2Csv"](export_scores, 'Result ' + this.class_id + ' ' + this.group_id);
    };
    GroupScoreComponent.prototype.getGroupScores = function (group_id) {
        var _this = this;
        this.scoresService.getGroupScores(group_id).subscribe(function (result) {
            for (var _i = 0, _a = result.students; _i < _a.length; _i++) {
                var student = _a[_i];
                var studentScore = {};
                student.student.active ? studentScore.name = student.student.firstName + ' ' + student.student.lastName : studentScore.name = '';
                studentScore.email = student.student.email;
                studentScore.exams = [];
                for (var _b = 0, _c = student.exams; _b < _c.length; _b++) {
                    var studentExam = _c[_b];
                    _this.addExamToHeader(studentExam.exam.name);
                    studentScore.exams.push(studentExam.score.score);
                }
                _this.studentsScores.push(studentScore);
            }
        }, function (error) {
            console.log(error);
        });
    };
    GroupScoreComponent.prototype.addExamToHeader = function (exam_name) {
        if (!this.header.find(function (obj) { return obj === exam_name; })) {
            this.header.push(exam_name);
        }
    };
    return GroupScoreComponent;
}());
GroupScoreComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-group-score',
        template: __webpack_require__(369),
        styles: [__webpack_require__(338)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_4__services_scores_service__["a" /* ScoresService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_scores_service__["a" /* ScoresService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* ActivatedRoute */]) === "function" && _c || Object])
], GroupScoreComponent);

var _a, _b, _c;
//# sourceMappingURL=group-score.component.js.map

/***/ }),
/* 88 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_students_service__ = __webpack_require__(54);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_group_service__ = __webpack_require__(31);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__execption_exception__ = __webpack_require__(19);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__models_group__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__models_class__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__models_exam__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__services_exams_service__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__models_user__ = __webpack_require__(14);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__services_navbar_service__ = __webpack_require__(25);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GroupViewComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};













var GroupViewComponent = (function () {
    function GroupViewComponent(router, route, authentication, students, groupsService, breadCrumbService, exception, examsService, navbarService) {
        this.router = router;
        this.route = route;
        this.authentication = authentication;
        this.students = students;
        this.groupsService = groupsService;
        this.breadCrumbService = breadCrumbService;
        this.exception = exception;
        this.examsService = examsService;
        this.navbarService = navbarService;
        this.model = {};
        this.allStudentsOfGroup = new Array();
    }
    GroupViewComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.upComingExams = [];
        this.onGoingExams = [];
        this.historyExams = [];
        this.order_date = true;
        this.order_date_text = 'most recent frist';
        this.route.parent.params.subscribe(function (parent_params) {
            _this.classId = +parent_params['class_id'];
            _this.route.params.subscribe(function (params) {
                _this.groupId = +params['group_id'];
                _this.getGroup();
                _this.getAllStudentsOfGroup();
                _this.setBreadCrumb();
            });
        });
    };
    GroupViewComponent.prototype.ngAfterViewInit = function () {
        panels();
        this.scroll();
    };
    // FIXME popup
    // ClickButton(): void {
    //   this.popup1.options = {
    //     header: '' ,
    //     color: 'red', // red, blue....
    //     widthProsentage: 40, // The with of the popou measured by browser width
    //     animationDuration: 1, // in seconds, 0 = no animation
    //     showButtons: true, // You can hide this in case you want to use custom buttons
    //     confirmBtnContent: 'OK', // The text on your confirm button
    //     cancleBtnContent: 'Cancel', // the text on your cancel button
    //     confirmBtnClass: 'btn btn-default', // your class for styling the confirm button
    //     cancleBtnClass: 'btn btn-default', // you class for styling the cancel button
    //     animation: 'fadeInDown' // 'fadeInLeft', 'fadeInRight', 'fadeInUp', 'bounceIn','bounceInDown'
    //   };
    //
    //   this.popup1.show(this.popup1.options);
    // }
    GroupViewComponent.prototype.cancelDelete = function () {
    };
    GroupViewComponent.prototype.confirmDelete = function () {
        this.removeGroup();
    };
    GroupViewComponent.prototype.setBreadCrumb = function () {
        // FIXME adiconar o nome do grupo
        this.breadCrumbService.setBreadCrum(['Class', this.getGroupName(), 'Group']);
    };
    GroupViewComponent.prototype.scroll = function () {
        $('.scroll').mCustomScrollbar({
            axis: 'y',
            autoHideScrollbar: true,
            scrollInertia: 20,
            advanced: {
                autoScrollOnFocus: false
            }
        }, { passive: true });
    };
    GroupViewComponent.prototype.toggleOrderDate = function () {
        this.order_date = !this.order_date;
        this.order_date_text = this.order_date ? 'most recent frist' : 'oldest frist';
        // TODO fazer função para mudar a ordem dos exames
    };
    GroupViewComponent.prototype.removeStudent = function (id) {
        var _this = this;
        this.students.deleteStudentById(this.groupId, id).subscribe(function (resultado) {
            var student = _this.allStudentsOfGroup.find(function (x) { return x.id === id; });
            var studentIndex = _this.allStudentsOfGroup.indexOf(student);
            _this.allStudentsOfGroup.splice(studentIndex, 1);
        }, function (error) {
            console.log(error);
        });
    };
    GroupViewComponent.prototype.getGroup = function () {
        var _this = this;
        this.groupsService.getGroupById(this.groupId).subscribe(function (result) {
            _this.group = _this.createGroup(result);
            // FIXME isto não devia ser feito aqui mas por causa dos nulls está aqui
            _this.getAllExamsOfGroup(_this.groupId);
        }, function (error) {
            // this.exception.errorHandlingInvalidToken(error);
        });
    };
    GroupViewComponent.prototype.getAllStudentsOfGroup = function () {
        var _this = this;
        this.allStudentsOfGroup = new Array();
        this.students.getUserByGroupId(this.groupId).subscribe(function (resultado) {
            for (var _i = 0, resultado_1 = resultado; _i < resultado_1.length; _i++) {
                var student = resultado_1[_i];
                var studen = { id: student.user.id, email: student.user.email, active: student.user.active, accepted: student.accepted };
                _this.allStudentsOfGroup.push(studen);
            }
        }, function (error) {
            console.log(error);
        });
    };
    GroupViewComponent.prototype.getGroupName = function () {
        return this.group ? this.group.name : '';
    };
    GroupViewComponent.prototype.getAllExamsOfGroup = function (group_id) {
        var _this = this;
        this.examsService.getExamsByGroupId(group_id).subscribe(function (result) {
            _this.upComingExams = [];
            _this.onGoingExams = [];
            _this.historyExams = [];
            if (result.exams.History) {
                _this.getAllHistory(result.exams.History);
            }
            if (result.exams.Ongoing) {
                _this.getAllOngoing(result.exams.Ongoing);
            }
            if (result.exams.Upcoming) {
                _this.getAllUpcoming(result.exams.Upcoming);
            }
        }, function (error) {
            console.log(error);
        });
    };
    GroupViewComponent.prototype.getAllHistory = function (exams) {
        for (var _i = 0, exams_1 = exams; _i < exams_1.length; _i++) {
            var exam = exams_1[_i];
            this.historyExams.push(this.createExam(exam));
        }
    };
    GroupViewComponent.prototype.getAllOngoing = function (exams) {
        for (var _i = 0, exams_2 = exams; _i < exams_2.length; _i++) {
            var exam = exams_2[_i];
            this.onGoingExams.push(this.createExam(exam));
        }
    };
    GroupViewComponent.prototype.getAllUpcoming = function (exams) {
        for (var _i = 0, exams_3 = exams; _i < exams_3.length; _i++) {
            var exam = exams_3[_i];
            this.upComingExams.push(this.createExam(exam));
        }
    };
    GroupViewComponent.prototype.createGroup = function (group) {
        var new_group = new __WEBPACK_IMPORTED_MODULE_7__models_group__["a" /* Group */](group.name);
        new_group.id = group.id;
        new_group.class = this.createClass(group._class);
        return new_group;
    };
    GroupViewComponent.prototype.createClass = function (class_r) {
        var newClass = new __WEBPACK_IMPORTED_MODULE_8__models_class__["a" /* Class */](class_r.name, class_r.abbreviation);
        newClass.id = class_r.id;
        newClass.user = this.createUser(class_r.teacher);
        return newClass;
    };
    GroupViewComponent.prototype.createUser = function (user) {
        var new_user = new __WEBPACK_IMPORTED_MODULE_11__models_user__["a" /* User */](user.id, user.email, user.firstName, user.lastName, user.type, '');
        return new_user;
    };
    GroupViewComponent.prototype.createExam = function (exam) {
        var examnew = new __WEBPACK_IMPORTED_MODULE_9__models_exam__["a" /* Exam */](exam.name, exam.beginDate, exam.duration);
        examnew.id = exam.id;
        examnew.group = this.group;
        return examnew;
    };
    GroupViewComponent.prototype.isTeacher = function () {
        return this.authentication.isTeacher();
    };
    GroupViewComponent.prototype.goToExamResult = function (exam) {
        var _this = this;
        if (this.isTeacher()) {
            this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id, 'results']);
        }
        else {
            this.examsService.getSubmissionsByExam(exam.id, this.authentication.getUserId()).subscribe(function (result) {
                if (result && result[0]) {
                    _this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id, 'submission', result[0].id]);
                }
            }, function (error) {
                console.log(error);
            });
        }
    };
    GroupViewComponent.prototype.goToExamOnGoing = function (exam) {
        if (this.isTeacher()) {
            this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id]);
        }
        else {
            this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id, 'submit']);
        }
    };
    GroupViewComponent.prototype.removeGroup = function () {
        var _this = this;
        this.groupsService.deleteGroupById(this.groupId).subscribe(function (result) {
            _this.navbarService.sendUpdate(true);
            _this.router.navigate(['/dashboard']);
        }, function (error) {
            console.log(error);
        });
    };
    GroupViewComponent.prototype.addStudent = function () {
        var _this = this;
        if (this.model.search && this.validateEmail(this.model.search)) {
            var addStudentNow = [];
            addStudentNow.push(this.model.search);
            this.students.postStudentByGroup(this.groupId, addStudentNow).subscribe(function (resultado) {
                if (resultado[0].user) {
                    var studen = { id: resultado[0].user.id, email: resultado[0].user.email, active: resultado[0].user.active, accepted: resultado[0].accepted };
                    _this.allStudentsOfGroup.push(studen);
                }
                _this.model.search = '';
            }, function (error) {
                console.log(error);
                _this.model.search = '';
            });
        }
    };
    GroupViewComponent.prototype.validateEmail = function (email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    };
    return GroupViewComponent;
}());
GroupViewComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-group-view',
        template: __webpack_require__(370),
        styles: [__webpack_require__(339)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["c" /* ActivatedRoute */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_3__services_students_service__["a" /* StudentsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_students_service__["a" /* StudentsService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_5__services_group_service__["a" /* GroupService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__services_group_service__["a" /* GroupService */]) === "function" && _e || Object, typeof (_f = typeof __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _f || Object, typeof (_g = typeof __WEBPACK_IMPORTED_MODULE_6__execption_exception__["a" /* Exception */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__execption_exception__["a" /* Exception */]) === "function" && _g || Object, typeof (_h = typeof __WEBPACK_IMPORTED_MODULE_10__services_exams_service__["a" /* ExamsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_10__services_exams_service__["a" /* ExamsService */]) === "function" && _h || Object, typeof (_j = typeof __WEBPACK_IMPORTED_MODULE_12__services_navbar_service__["a" /* NavbarService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_12__services_navbar_service__["a" /* NavbarService */]) === "function" && _j || Object])
], GroupViewComponent);

var _a, _b, _c, _d, _e, _f, _g, _h, _j;
//# sourceMappingURL=group-view.component.js.map

/***/ }),
/* 89 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__models_question__ = __webpack_require__(30);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_categories_service__ = __webpack_require__(52);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionCreateComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var QuestionCreateComponent = (function () {
    function QuestionCreateComponent(router, categorie, route, breadCrumb) {
        this.router = router;
        this.categorie = categorie;
        this.route = route;
        this.breadCrumb = breadCrumb;
        this.saveAll = false;
        this.justOnQuestion = false;
        this.questionsAddOk = false;
        this.categories = [];
        this.breadCrumb.setBreadCrum(['Class > Question > New']);
    }
    QuestionCreateComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.questions = [];
        this.questions.push(new __WEBPACK_IMPORTED_MODULE_2__models_question__["a" /* Question */](__WEBPACK_IMPORTED_MODULE_2__models_question__["a" /* Question */]._Normal, ''));
        this.route.params.subscribe(function (params) {
            _this.classId = +params['class_id'];
            _this.getAllCategories();
        });
    };
    QuestionCreateComponent.prototype.getAllCategories = function () {
        var _this = this;
        this.categorie.getCategoriesByClasse(this.classId).subscribe(function (resultado) {
            for (var _i = 0, resultado_1 = resultado; _i < resultado_1.length; _i++) {
                var categorie = resultado_1[_i];
                _this.categories.push(categorie);
            }
        }, function (error) {
            console.log(error);
        });
    };
    QuestionCreateComponent.prototype.saveQuestions = function () {
        // this.router.navigate();
    };
    QuestionCreateComponent.prototype.moreQuestion = function () {
        this.saveAll = false;
        this.questions.push(new __WEBPACK_IMPORTED_MODULE_2__models_question__["a" /* Question */](__WEBPACK_IMPORTED_MODULE_2__models_question__["a" /* Question */]._Normal, ''));
    };
    QuestionCreateComponent.prototype.saveQuestion = function () {
        var _this = this;
        var length = this.questions.length;
        this.saveAll = true;
        if (length === this.questions.length) {
            this.saveAll = false;
            setTimeout(function () {
                _this.saveAll = true;
            }, 1);
        }
        else {
            this.saveAll = false;
        }
    };
    QuestionCreateComponent.prototype.questionPostOk = function ($event) {
        this.deleteQuestion($event);
        this.questionsAddOk = true;
        this.justOnQuestion = false;
    };
    QuestionCreateComponent.prototype.saveQuestionButton = function ($event) {
        this.deleteQuestion($event);
        this.justOnQuestion = true;
        this.questionsAddOk = false;
        this.saveAll = false;
    };
    QuestionCreateComponent.prototype.deleteQuestion = function ($event) {
        var indexQuestion = this.questions.indexOf($event);
        this.questions.splice(indexQuestion, 1);
    };
    QuestionCreateComponent.prototype.removeQuestion = function ($event) {
        this.deleteQuestion($event);
    };
    return QuestionCreateComponent;
}());
QuestionCreateComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-question-create',
        template: __webpack_require__(374),
        styles: [__webpack_require__(343)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__services_categories_service__["a" /* CategoriesService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_categories_service__["a" /* CategoriesService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* ActivatedRoute */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _d || Object])
], QuestionCreateComponent);

var _a, _b, _c, _d;
//# sourceMappingURL=question-create.component.js.map

/***/ }),
/* 90 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_questions_service__ = __webpack_require__(34);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_categories_service__ = __webpack_require__(52);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var QuestionsComponent = (function () {
    function QuestionsComponent(router, question, route, categorie, breadCrumb) {
        var _this = this;
        this.router = router;
        this.question = question;
        this.route = route;
        this.categorie = categorie;
        this.breadCrumb = breadCrumb;
        this.questionModel = {};
        this.route.params.subscribe(function (params) {
            _this.classId = +params['class_id'];
        });
        this.dificultys = [];
        this.categoriesName = [];
        this.allQuestion = new Map();
        this.breadCrumb.setBreadCrum(['Class > Question > View']);
    }
    QuestionsComponent.prototype.ngOnInit = function () {
        this.questions = [];
        this.getAllQuestions();
        this.dificultys.push({ value: 1, dificulty: 'Easy' });
        this.dificultys.push({ value: 2, dificulty: 'Normal' });
        this.dificultys.push({ value: 3, dificulty: 'Hard' });
        this.questionModel.difi = 1;
    };
    QuestionsComponent.prototype.getQuestion = function () {
        var _this = this;
        this.questions = [];
        setTimeout(function () {
            if (_this.allQuestion.get(_this.questionModel.category)) {
                if (_this.allQuestion.get(_this.questionModel.category).get(+_this.questionModel.difi)) {
                    _this.questions = _this.allQuestion.get(_this.questionModel.category).get(+_this.questionModel.difi);
                }
            }
        }, 1);
    };
    QuestionsComponent.prototype.getAllCategories = function () {
        var _this = this;
        this.categorie.getCategoriesByClasse(this.classId).subscribe(function (result) {
            var haveCateg = 0;
            for (var _i = 0, result_1 = result; _i < result_1.length; _i++) {
                var categ = result_1[_i];
                _this.categoriesName.push(categ);
                haveCateg++;
            }
            if (haveCateg !== 0) {
                _this.questionModel.category = _this.categoriesName[0];
                _this.getQuestion();
            }
        }, function (error) {
            console.log(error);
        });
    };
    QuestionsComponent.prototype.getAllQuestions = function () {
        var _this = this;
        this.question.getAllQuestionsFromClasse(this.classId).subscribe(function (result) {
            for (var _i = 0, result_2 = result; _i < result_2.length; _i++) {
                var quest = result_2[_i];
                if (_this.allQuestion.get(quest.category)) {
                    if (_this.allQuestion.get(quest.category).get(+quest.difficulty)) {
                        _this.allQuestion.get(quest.category).get(+quest.difficulty).push(quest);
                    }
                    else {
                        var newArrayQuest = new Array();
                        newArrayQuest.push(quest);
                        _this.allQuestion.get(quest.category).set(+quest.difficulty, newArrayQuest);
                    }
                }
                else {
                    var newArrayQuest = new Array();
                    newArrayQuest.push(quest);
                    var newMapDificulty = new Map();
                    newMapDificulty.set(+quest.difficulty, newArrayQuest);
                    _this.allQuestion.set(quest.category, newMapDificulty);
                }
            }
            _this.getAllCategories();
        }, function (error) {
            console.log(error);
        });
    };
    return QuestionsComponent;
}());
QuestionsComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-questions',
        template: __webpack_require__(375),
        styles: [__webpack_require__(344)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_questions_service__["a" /* QuestionsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_questions_service__["a" /* QuestionsService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* ActivatedRoute */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_3__services_categories_service__["a" /* CategoriesService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_categories_service__["a" /* CategoriesService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _e || Object])
], QuestionsComponent);

var _a, _b, _c, _d, _e;
//# sourceMappingURL=questions.component.js.map

/***/ }),
/* 91 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_group_service__ = __webpack_require__(31);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_classes_service__ = __webpack_require__(41);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__models_notification__ = __webpack_require__(101);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__services_notification_service__ = __webpack_require__(53);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_app_models_class__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__models_user__ = __webpack_require__(14);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__models_group__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__services_exams_service__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__execption_exception__ = __webpack_require__(19);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__models_exam__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14_rxjs_add_operator_filter__ = __webpack_require__(391);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14_rxjs_add_operator_filter___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_14_rxjs_add_operator_filter__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__services_navbar_service__ = __webpack_require__(25);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DashboardComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
















var DashboardComponent = (function () {
    function DashboardComponent(router, route, authentication, breadCrumb, groupsService, classesService, notificationsService, examsService, exception, navbarService) {
        this.router = router;
        this.route = route;
        this.authentication = authentication;
        this.breadCrumb = breadCrumb;
        this.groupsService = groupsService;
        this.classesService = classesService;
        this.notificationsService = notificationsService;
        this.examsService = examsService;
        this.exception = exception;
        this.navbarService = navbarService;
    }
    DashboardComponent.prototype.ngOnInit = function () {
        this.notifications = [];
        this.examsOnGoing = [];
        this.page_navigation_toggled = false;
        this.initBreadCrum();
        this.initNavbarUpdate(); // Update Navbar
        this.initExamsOnGoing(); // Exams On going
        this.breadCrumb.setBreadCrum(['Dashboard']);
    };
    DashboardComponent.prototype.ngOnDestroy = function () {
        clearInterval(this.intervalExamsOnGoing);
        clearInterval(this.intervalNotification);
    };
    DashboardComponent.prototype.ngAfterViewInit = function () {
        this.rezise();
        if (this.isStudent()) {
            this.initNotification();
        }
    };
    DashboardComponent.prototype.ngOnChanges = function () {
    };
    DashboardComponent.prototype.rezise = function () {
        x_navigation();
        page_content_onresize();
    };
    DashboardComponent.prototype.initNavbarUpdate = function () {
        var _this = this;
        this.createNavbarStructure();
        this.updateNavbar();
        this.navbarService.navbarObservable.subscribe(function (value) {
            _this.updateNavbar();
        });
    };
    DashboardComponent.prototype.initBreadCrum = function () {
        var _this = this;
        this.breadCrumb.breadCrumDate.subscribe(function (value) {
            _this.nameInToggleNavigation = value.pop();
        });
    };
    DashboardComponent.prototype.initExamsOnGoing = function () {
        var _this = this;
        this.getExamsOnGoing();
        this.intervalExamsOnGoing = setInterval(function () {
            _this.getExamsOnGoing();
        }, 1000 * 60);
    };
    DashboardComponent.prototype.initNotification = function () {
        var _this = this;
        this.getNotifications();
        this.intervalNotification = setInterval(function () {
            _this.getNotifications();
        }, 1000 * 60);
    };
    DashboardComponent.prototype.getExamsOnGoing = function () {
        var _this = this;
        this.examsService.getExamsOnGoingByUserId(this.authentication.getUserId()).subscribe(function (result) {
            _this.examsOnGoing = [];
            _this.getOngoing(result);
        }, function (error) {
            _this.exception.errorHandlingInvalidToken(error);
        });
    };
    DashboardComponent.prototype.getNotifications = function () {
        var _this = this;
        this.notificationsService.getUserNotification(this.authentication.getUserId()).subscribe(function (result) {
            _this.notifications = [];
            for (var _i = 0, result_1 = result; _i < result_1.length; _i++) {
                var not = result_1[_i];
                var notification = new __WEBPACK_IMPORTED_MODULE_6__models_notification__["a" /* Notification */]();
                notification.id = not.id;
                notification.name = not.name;
                notification.type = not.type;
                notification.group = new __WEBPACK_IMPORTED_MODULE_10__models_group__["a" /* Group */](not.group.name);
                notification.group.id = not.group.id;
                notification.group.name = not.group.name;
                notification.group.class = new __WEBPACK_IMPORTED_MODULE_8_app_models_class__["a" /* Class */](not.group._class.name, not.group._class.abbreviation);
                notification.group.class.id = not.group._class.id;
                notification.group.class.user = new __WEBPACK_IMPORTED_MODULE_9__models_user__["a" /* User */](not.group._class.teacher.id, not.group._class.teacher.email, not.group._class.teacher.firstName, not.group._class.teacher.lastName, __WEBPACK_IMPORTED_MODULE_9__models_user__["a" /* User */]._Teacher, '');
                _this.notifications.push(not);
            }
        }, function (error) {
            console.log(error);
        });
    };
    DashboardComponent.prototype.getClasses = function () {
        var _this = this;
        this.classesService.getAllClassesByUser(this.authentication.getUserId()).subscribe(function (result) {
            var classes_dash = _this.collapse_struture[3];
            var _loop_1 = function (resul_class) {
                var class_dash = classes_dash.children.find(function (obj) { return resul_class.id === obj.id; });
                if (class_dash) {
                    _this.getGroups(class_dash, resul_class.id);
                }
                else {
                    classes_dash.children.push({
                        level: 2,
                        id: resul_class.id,
                        name: resul_class.abbreviation,
                        route: ['/dashboard', 'classes', resul_class.id],
                        isCollapsed: false,
                        children: []
                    });
                    _this.getGroups(classes_dash.children.find(function (obj) { return resul_class.id === obj.id; }), resul_class.id);
                }
            };
            // add class
            for (var _i = 0, result_2 = result; _i < result_2.length; _i++) {
                var resul_class = result_2[_i];
                _loop_1(resul_class);
            }
        }, function (error) {
            console.log(error);
        });
    };
    DashboardComponent.prototype.getGroups = function (class_dash, class_id) {
        this.groupsService.getGroupsClassByUser(class_id, this.authentication.getUserId()).subscribe(function (result) {
            class_dash.children = class_dash.children.filter(function (obj_class) { return result.filter(function (obj_res) { return obj_res.id === obj_class.id; }).length > 0; });
            var _loop_2 = function (group) {
                var group_dash = class_dash.children.find(function (obj) { return group.id === obj.id; });
                if (!group_dash) {
                    class_dash.children.push({
                        level: 3,
                        id: group.id,
                        name: group.name,
                        route: ['/dashboard', 'classes', class_id, 'groups', '' + group.id],
                        isCollapsed: false
                    });
                }
            };
            for (var _i = 0, result_3 = result; _i < result_3.length; _i++) {
                var group = result_3[_i];
                _loop_2(group);
            }
        }, function (error) {
            console.log(error);
        });
    };
    DashboardComponent.prototype.createNavbarStructure = function () {
        this.collapse_struture = [
            { id: 0, level: 1, name: 'Dashboard', route: ['/dashboard'], isCollapsed: false },
            { id: 1, level: 1, name: 'Schedule', route: ['/dashboard', 'schedule'], isCollapsed: false },
            { id: 2, level: 1, name: 'Results', route: ['/dashboard', 'results'], isCollapsed: false },
            { id: 3, level: 1, name: 'Classes', route: [], isCollapsed: false, children: [] }
        ];
    };
    DashboardComponent.prototype.navigateRoute = function (route, collapse_node, node_ids) {
        switch (node_ids.length) {
            case 1: {
                this.clearCollapseLevel(1, node_ids[0]);
                this.clearCollapseLevel(2, -1);
                this.clearCollapseLevel(3, -1);
                break;
            }
            case 2: {
                this.clearCollapseLevel(1, node_ids[0]);
                this.clearCollapseLevel(2, node_ids[1]);
                this.clearCollapseLevel(3, -1);
                break;
            }
            case 3: {
                this.clearCollapseLevel(1, node_ids[0]);
                this.clearCollapseLevel(2, node_ids[1]);
                this.clearCollapseLevel(3, node_ids[2]);
                break;
            }
        }
        collapse_node.isCollapsed = true;
        this.rezise();
        if (route.length > 0) {
            this.router.navigate(route);
        }
    };
    DashboardComponent.prototype.clearCollapseLevel = function (level, noclear) {
        switch (level) {
            case 1: {
                this.collapse_struture.map(function (obj) {
                    obj.id === noclear ? obj.isCollapsed = true : obj.isCollapsed = false;
                });
                break;
            }
            case 2: {
                this.collapse_struture.map(function (obj_parent) {
                    if (obj_parent.children) {
                        obj_parent.children.map(function (obj) {
                            obj.id === noclear ? obj.isCollapsed = true : obj.isCollapsed = false;
                        });
                    }
                });
                break;
            }
            case 3: {
                this.collapse_struture.map(function (obj_grand) {
                    if (obj_grand.children) {
                        obj_grand.children.map(function (obj_parent) {
                            if (obj_parent.children) {
                                obj_parent.children.map(function (obj) {
                                    obj.id === noclear ? obj.isCollapsed = true : obj.isCollapsed = false;
                                });
                            }
                        });
                    }
                });
                break;
            }
        }
    };
    DashboardComponent.prototype.toggledPageNavigation = function () {
        this.page_navigation_toggled = !this.page_navigation_toggled;
    };
    DashboardComponent.prototype.getUserName = function () {
        return this.authentication.getUserName();
    };
    DashboardComponent.prototype.getUserType = function () {
        return this.authentication.userLogged.type;
    };
    DashboardComponent.prototype.logout = function () {
        this.authentication.logout();
        this.router.navigate(['/']);
    };
    DashboardComponent.prototype.isTeacher = function () {
        return this.authentication.isTeacher();
    };
    DashboardComponent.prototype.isStudent = function () {
        return this.authentication.isSudent();
    };
    DashboardComponent.prototype.getOngoing = function (exams) {
        for (var _i = 0, exams_1 = exams; _i < exams_1.length; _i++) {
            var exam = exams_1[_i];
            this.examsOnGoing.push(this.createExam(exam));
        }
    };
    DashboardComponent.prototype.createExam = function (exam) {
        var teacher = exam.group._class.teacher;
        var _teacher = new __WEBPACK_IMPORTED_MODULE_9__models_user__["a" /* User */](teacher.id, teacher.email, teacher.firstName, teacher.lastName, teacher.type, '');
        var classe = exam.group._class;
        var _classe = new __WEBPACK_IMPORTED_MODULE_8_app_models_class__["a" /* Class */](classe.name, classe.abbreviation);
        _classe.id = classe.id;
        _classe.user = _teacher;
        var group = exam.group;
        var _group = new __WEBPACK_IMPORTED_MODULE_10__models_group__["a" /* Group */](group.name);
        _group.id = group.id;
        _group.class = _classe;
        var examnew = new __WEBPACK_IMPORTED_MODULE_13__models_exam__["a" /* Exam */](exam.name, exam.beginDate, exam.duration);
        examnew.id = exam.id;
        examnew.group = _group;
        return examnew;
    };
    DashboardComponent.prototype.updateNavbar = function () {
        this.getClasses();
    };
    return DashboardComponent;
}());
DashboardComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-dashboard',
        template: __webpack_require__(376),
        styles: [__webpack_require__(345)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["c" /* ActivatedRoute */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_4__services_group_service__["a" /* GroupService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_group_service__["a" /* GroupService */]) === "function" && _e || Object, typeof (_f = typeof __WEBPACK_IMPORTED_MODULE_5__services_classes_service__["a" /* ClassesService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__services_classes_service__["a" /* ClassesService */]) === "function" && _f || Object, typeof (_g = typeof __WEBPACK_IMPORTED_MODULE_7__services_notification_service__["a" /* NotificationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_7__services_notification_service__["a" /* NotificationService */]) === "function" && _g || Object, typeof (_h = typeof __WEBPACK_IMPORTED_MODULE_11__services_exams_service__["a" /* ExamsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_11__services_exams_service__["a" /* ExamsService */]) === "function" && _h || Object, typeof (_j = typeof __WEBPACK_IMPORTED_MODULE_12__execption_exception__["a" /* Exception */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_12__execption_exception__["a" /* Exception */]) === "function" && _j || Object, typeof (_k = typeof __WEBPACK_IMPORTED_MODULE_15__services_navbar_service__["a" /* NavbarService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_15__services_navbar_service__["a" /* NavbarService */]) === "function" && _k || Object])
], DashboardComponent);

var _a, _b, _c, _d, _e, _f, _g, _h, _j, _k;
//# sourceMappingURL=dashboard.component.js.map

/***/ }),
/* 92 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_classes_service__ = __webpack_require__(41);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__execption_exception__ = __webpack_require__(19);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__services_navbar_service__ = __webpack_require__(25);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DefaultComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var DefaultComponent = (function () {
    function DefaultComponent(authentication, breadCrumb, classes, exception, router, navbarService) {
        this.authentication = authentication;
        this.breadCrumb = breadCrumb;
        this.classes = classes;
        this.exception = exception;
        this.router = router;
        this.navbarService = navbarService;
        this.new_class_add = {};
    }
    DefaultComponent.prototype.ngOnInit = function () {
        this.classAlreadyExists = false;
        this.classAsCreate = false;
        this.breadCrumb.setBreadCrum(['Dashboard']);
    };
    DefaultComponent.prototype.ngOnChanges = function (changes) {
    };
    DefaultComponent.prototype.ngAfterViewInit = function () {
        this.rezise();
        this.scroll();
    };
    DefaultComponent.prototype.rezise = function () {
        x_navigation();
        page_content_onresize();
    };
    DefaultComponent.prototype.scroll = function () {
        $('.scroll').mCustomScrollbar({
            axis: 'y',
            autoHideScrollbar: true,
            scrollInertia: 20,
            advanced: {
                autoScrollOnFocus: false
            }
        }, { passive: true });
    };
    DefaultComponent.prototype.addClass = function () {
        var _this = this;
        this.classAsCreate = false;
        this.classes.createClasseByUser(this.authentication.getUserId(), this.new_class_add.nameClass, this.new_class_add.abbrev).subscribe(function (resultado) {
            _this.classAsCreate = true;
            _this.new_class_add = {};
            _this.navbarService.sendUpdate(true);
            _this.router.navigate(['/dashboard', 'classes', resultado.id]);
        }, function (error) {
            _this.exception.errorHandlingInvalidToken(error);
            if (error.status === 406) {
                _this.classAlreadyExists = _this.exception.errorHandlingCreateClass(error);
            }
            _this.classAsCreate = false;
            console.log(error);
        });
    };
    DefaultComponent.prototype.changeAlreadyExists = function () {
        this.classAlreadyExists = false;
    };
    DefaultComponent.prototype.isTeacher = function () {
        return this.authentication.isTeacher();
    };
    return DefaultComponent;
}());
DefaultComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-default',
        template: __webpack_require__(377),
        styles: [__webpack_require__(346)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__services_classes_service__["a" /* ClassesService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_classes_service__["a" /* ClassesService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__execption_exception__["a" /* Exception */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__execption_exception__["a" /* Exception */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_5__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__angular_router__["a" /* Router */]) === "function" && _e || Object, typeof (_f = typeof __WEBPACK_IMPORTED_MODULE_6__services_navbar_service__["a" /* NavbarService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__services_navbar_service__["a" /* NavbarService */]) === "function" && _f || Object])
], DefaultComponent);

var _a, _b, _c, _d, _e, _f;
//# sourceMappingURL=default.component.js.map

/***/ }),
/* 93 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__models_exam__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_group__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_scores_service__ = __webpack_require__(42);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__models_class__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__models_user__ = __webpack_require__(14);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__models_submission__ = __webpack_require__(102);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ResultsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};









var ResultsComponent = (function () {
    function ResultsComponent(authentication, breadCrumbService, scoresService) {
        this.authentication = authentication;
        this.breadCrumbService = breadCrumbService;
        this.scoresService = scoresService;
    }
    ResultsComponent.prototype.ngOnInit = function () {
        this.submissionResults = [];
        this.getResults();
        this.setBreadCrumb();
    };
    ResultsComponent.prototype.ngAfterViewInit = function () {
        this.rezise();
    };
    ResultsComponent.prototype.rezise = function () {
        x_navigation();
        page_content_onresize();
    };
    ResultsComponent.prototype.setBreadCrumb = function () {
        this.breadCrumbService.setBreadCrum(['Results']);
    };
    ResultsComponent.prototype.isTeacher = function () {
        return this.authentication.isTeacher();
    };
    ResultsComponent.prototype.isStudent = function () {
        return this.authentication.isSudent();
    };
    ResultsComponent.prototype.getResults = function () {
        var _this = this;
        this.scoresService.getUserScore(this.authentication.getUserId()).subscribe(function (result) {
            _this.submissionResults = [];
            for (var _i = 0, _a = result.groups; _i < _a.length; _i++) {
                var group = _a[_i];
                var new_group = _this.createGroup(group.group);
                for (var _b = 0, _c = group.exams; _b < _c.length; _b++) {
                    var exam = _c[_b];
                    var new_submission = _this.createSubmission(exam.score);
                    new_submission.exam = _this.createExam(exam.exam);
                    new_submission.exam.group = new_group;
                    _this.submissionResults.push(new_submission);
                }
            }
        }, function (error) {
            console.log(error);
        });
    };
    ResultsComponent.prototype.createGroup = function (group) {
        var new_group = new __WEBPACK_IMPORTED_MODULE_4__models_group__["a" /* Group */](group.name);
        new_group.id = group.id;
        new_group.class = this.createClass(group._class);
        return new_group;
    };
    ResultsComponent.prototype.createClass = function (class_r) {
        var new_class = new __WEBPACK_IMPORTED_MODULE_6__models_class__["a" /* Class */](class_r.name, class_r.abbreviation);
        new_class.id = class_r.id;
        new_class.user = this.createUser(class_r.teacher);
        return new_class;
    };
    ResultsComponent.prototype.createUser = function (user) {
        var new_user = new __WEBPACK_IMPORTED_MODULE_7__models_user__["a" /* User */](user.id, user.email, user.firstName, user.lastName, user.type, '');
        return new_user;
    };
    ResultsComponent.prototype.createExam = function (exam) {
        var new_exam = new __WEBPACK_IMPORTED_MODULE_3__models_exam__["a" /* Exam */](exam.name, exam.beginExam, exam.duration);
        new_exam.id = exam.id;
        return new_exam;
    };
    ResultsComponent.prototype.createSubmission = function (submission) {
        var new_submission = new __WEBPACK_IMPORTED_MODULE_8__models_submission__["a" /* Submission */](submission.submissionID, submission.score);
        return new_submission;
    };
    ResultsComponent.prototype.refresResults = function () {
        this.getResults();
    };
    return ResultsComponent;
}());
ResultsComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-results',
        template: __webpack_require__(380),
        styles: [__webpack_require__(349)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_5__services_scores_service__["a" /* ScoresService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__services_scores_service__["a" /* ScoresService */]) === "function" && _c || Object])
], ResultsComponent);

var _a, _b, _c;
//# sourceMappingURL=results.component.js.map

/***/ }),
/* 94 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ap_angular2_fullcalendar__ = __webpack_require__(105);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ap_angular2_fullcalendar___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_ap_angular2_fullcalendar__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_exams_service__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ScheduleComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var ScheduleComponent = (function () {
    function ScheduleComponent(breadCrumb, exams, authentication) {
        this.breadCrumb = breadCrumb;
        this.exams = exams;
        this.authentication = authentication;
        this.calendarOptions = {
            // height: 'parent',
            fixedWeekCount: false,
            defaultDate: '2016-09-12',
            editable: false,
            eventLimit: true,
            events: []
        };
    }
    ScheduleComponent.prototype.changeCalendarView = function (view) {
        this.myCalendar.fullCalendar('changeView', view);
    };
    ScheduleComponent.prototype.ngOnInit = function () {
        this.putDateNowOnEvent();
        this.breadCrumb.setBreadCrum(['Schedule']);
        this.getAllExams();
    };
    ScheduleComponent.prototype.ngAfterViewInit = function () {
        x_navigation();
        page_content_onresize();
    };
    ScheduleComponent.prototype.putDateNowOnEvent = function () {
        var date = new Date();
        if (date.getUTCMonth() < 9) {
            if (date.getUTCDate() < 10) {
                this.calendarOptions.defaultDate = date.getFullYear() + '-0' + (date.getUTCMonth() + 1) + '-0' + date.getUTCDate();
            }
            else {
                this.calendarOptions.defaultDate = date.getFullYear() + '-0' + (date.getUTCMonth() + 1) + '-' + date.getUTCDate();
            }
        }
        else {
            this.calendarOptions.defaultDate = date.getFullYear() + '-' + (date.getUTCMonth() + 1) + '-' + date.getUTCDate();
        }
    };
    ScheduleComponent.prototype.getAllExams = function () {
        var _this = this;
        this.exams.getExamsByUserId(this.authentication.getUserId()).subscribe(function (resultado) {
            if (resultado.exams.History) {
                _this.getExams(resultado.exams.History);
            }
            if (resultado.exams.Ongoing) {
                _this.getExams(resultado.exams.Ongoing);
            }
            if (resultado.exams.Upcoming) {
                _this.getExams(resultado.exams.Upcoming);
            }
        }, function (error) {
            console.log(error);
        });
    };
    ScheduleComponent.prototype.getExams = function (exams) {
        for (var _i = 0, exams_1 = exams; _i < exams_1.length; _i++) {
            var exam = exams_1[_i];
            this.calendarOptions.events.push(this.createDateExam(exam));
        }
    };
    ScheduleComponent.prototype.createDateExam = function (exam) {
        var calendarExam = { start: this.createDate(exam, 0), end: this.createDate(exam, exam.duration), title: exam.group._class.name };
        return calendarExam;
    };
    ScheduleComponent.prototype.createDate = function (exam, duration) {
        var dateExam = new Date(exam.beginDate + (duration * 60 * 1000));
        var day = dateExam.getDate();
        var month = dateExam.getUTCMonth() + 1;
        var startday = '' + day;
        var startmonth = '' + month;
        if (day < 10) {
            startday = '0' + day;
        }
        if (month < 10) {
            startmonth = '0' + month;
        }
        var startyear = dateExam.getFullYear();
        return startyear + '-' + startmonth + '-' + startday;
    };
    return ScheduleComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])(__WEBPACK_IMPORTED_MODULE_1_ap_angular2_fullcalendar__["CalendarComponent"]),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1_ap_angular2_fullcalendar__["CalendarComponent"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1_ap_angular2_fullcalendar__["CalendarComponent"]) === "function" && _a || Object)
], ScheduleComponent.prototype, "myCalendar", void 0);
ScheduleComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-schedule',
        template: __webpack_require__(381),
        styles: [__webpack_require__(350)]
    }),
    __metadata("design:paramtypes", [typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_breadcrumb_service__["a" /* BreadCrumbService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__services_exams_service__["a" /* ExamsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_exams_service__["a" /* ExamsService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _d || Object])
], ScheduleComponent);

var _a, _b, _c, _d;
//# sourceMappingURL=schedule.component.js.map

/***/ }),
/* 95 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HomeComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var HomeComponent = (function () {
    function HomeComponent() {
    }
    HomeComponent.prototype.ngOnInit = function () {
    };
    return HomeComponent;
}());
HomeComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-home',
        template: __webpack_require__(382),
        styles: [__webpack_require__(351)]
    }),
    __metadata("design:paramtypes", [])
], HomeComponent);

//# sourceMappingURL=home.component.js.map

/***/ }),
/* 96 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__(4);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var LoginComponent = (function () {
    function LoginComponent(authentication, router) {
        this.authentication = authentication;
        this.router = router;
        this.user = {};
        this.loading = false;
        this.invalidEmail = false;
        this.emailOrPasswordWrong = false;
    }
    LoginComponent.prototype.ngOnInit = function () {
    };
    LoginComponent.prototype.onSubmit = function () {
        var _this = this;
        this.loading = true;
        this.emailOrPasswordWrong = false;
        var res;
        if (this.validateEmail(this.user.email) && this.user.password) {
            this.invalidEmail = false;
            this.authentication.login(this.user.email, this.user.password).subscribe(function (result) {
                _this.router.navigate(['/dashboard']);
                res = true;
            }, function (error) {
                _this.emailOrPasswordWrong = true;
                console.log(error);
                res = false;
            });
        }
        else {
            if (this.user.email && this.user.password) {
                this.invalidEmail = true;
            }
            res = false;
        }
        this.loading = false;
        return res;
    };
    LoginComponent.prototype.validateEmail = function (email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    };
    return LoginComponent;
}());
LoginComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-login',
        template: __webpack_require__(383),
        styles: [__webpack_require__(352)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */]) === "function" && _b || Object])
], LoginComponent);

var _a, _b;
//# sourceMappingURL=login.component.js.map

/***/ }),
/* 97 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__models_notification__ = __webpack_require__(101);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_notification_service__ = __webpack_require__(53);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_group__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__models_class__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_app_models_user__ = __webpack_require__(14);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return NotificationComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var NotificationComponent = (function () {
    function NotificationComponent(authentication, notificationsService) {
        this.authentication = authentication;
        this.notificationsService = notificationsService;
    }
    NotificationComponent.prototype.ngOnInit = function () {
        this.notifications = [];
        this.getNotifications();
    };
    NotificationComponent.prototype.getNotifications = function () {
        var _this = this;
        this.notificationsService.getUserNotification(this.authentication.getUserId()).subscribe(function (result) {
            _this.notifications = [];
            for (var _i = 0, result_1 = result; _i < result_1.length; _i++) {
                var not = result_1[_i];
                var notification = new __WEBPACK_IMPORTED_MODULE_1__models_notification__["a" /* Notification */]();
                notification.id = not.id;
                notification.name = not.name;
                notification.type = not.type;
                notification.group = new __WEBPACK_IMPORTED_MODULE_4__models_group__["a" /* Group */](not.group.name);
                notification.group.id = not.group.id;
                notification.group.name = not.group.name;
                notification.group.class = new __WEBPACK_IMPORTED_MODULE_5__models_class__["a" /* Class */](not.group._class.name, not.group._class.abbreviation);
                notification.group.class.id = not.group._class.id;
                notification.group.class.user = new __WEBPACK_IMPORTED_MODULE_6_app_models_user__["a" /* User */](not.group._class.teacher.id, not.group._class.teacher.email, not.group._class.teacher.firstName, not.group._class.teacher.lastName, __WEBPACK_IMPORTED_MODULE_6_app_models_user__["a" /* User */]._Teacher, '');
                _this.notifications.push(not);
            }
        }, function (error) {
            console.log(error);
        });
    };
    NotificationComponent.prototype.declineNotification = function (invitition_id) {
        var _this = this;
        this.notificationsService.declineNotification(invitition_id).subscribe(function (result) {
            _this.notifications = _this.notifications.filter(function (obj) { return obj.id !== invitition_id; });
        }, function (error) {
            console.log(error);
        });
    };
    NotificationComponent.prototype.acceptNotification = function (invitition_id) {
        var _this = this;
        this.notificationsService.acceptNotification(invitition_id).subscribe(function (result) {
            _this.notifications = _this.notifications.filter(function (obj) { return obj.id !== invitition_id; });
        }, function (error) {
            console.log(error);
        });
    };
    NotificationComponent.prototype.refreshNotifications = function () {
        this.getNotifications();
    };
    NotificationComponent.prototype.getNotificationsFilter = function () {
        return this.notifications.filter(function (obj) { return obj.group; });
    };
    return NotificationComponent;
}());
NotificationComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-notification',
        template: __webpack_require__(384),
        styles: [__webpack_require__(353)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__services_notification_service__["a" /* NotificationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_notification_service__["a" /* NotificationService */]) === "function" && _b || Object])
], NotificationComponent);

var _a, _b;
//# sourceMappingURL=notification.component.js.map

/***/ }),
/* 98 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_app_services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_user_service__ = __webpack_require__(104);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__execption_exception__ = __webpack_require__(19);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ProfileComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ProfileComponent = (function () {
    function ProfileComponent(autentication, usersService, exception) {
        this.autentication = autentication;
        this.usersService = usersService;
        this.exception = exception;
        this.user = {};
        this.loading = false;
        this.differentPassword = false;
        this.updatedSuccess = false;
        this.updatedError = false;
    }
    ProfileComponent.prototype.ngOnInit = function () {
        this.getUser();
        x_navigation();
        page_content_onresize();
    };
    ProfileComponent.prototype.getUser = function () {
        this.user.firstName = this.autentication.userLogged.firstName;
        this.user.lastName = this.autentication.userLogged.lastName;
        this.user.email = this.autentication.userLogged.email;
        this.user.type = this.autentication.userLogged.type;
    };
    ProfileComponent.prototype.updateProfile = function () {
        var _this = this;
        // TODO fazer o service do update do user
        this.usersService.updateUserById(this.autentication.getUserId(), this.user.firstName, this.user.lastName, this.user.password).subscribe(function (result) {
            _this.autentication.userLogged.firstName = result.firstName;
            _this.autentication.userLogged.lastName = result.lastName;
            _this.updatedSuccess = true;
        }, function (error) {
            _this.exception.errorHandlingInvalidToken(error);
            _this.updatedError = true;
            // TODO ver se isto funciona do error
        });
    };
    ProfileComponent.prototype.onSubmit = function () {
        this.updatedError = false;
        this.updateProfile();
    };
    ProfileComponent.prototype.validPassword = function () {
        if ((this.user.password !== this.user.confirmPassword) && this.user.password && this.user.confirmPassword && this.validAll()) {
            this.differentPassword = true;
            return false;
        }
        else {
            this.differentPassword = false;
        }
        return true;
    };
    ProfileComponent.prototype.validAll = function () {
        return this.user.firstName && this.user.lastName;
    };
    return ProfileComponent;
}());
ProfileComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-profile',
        template: __webpack_require__(385),
        styles: [__webpack_require__(354)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1_app_services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1_app_services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_user_service__["a" /* UserService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_user_service__["a" /* UserService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__execption_exception__["a" /* Exception */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__execption_exception__["a" /* Exception */]) === "function" && _c || Object])
], ProfileComponent);

var _a, _b, _c;
//# sourceMappingURL=profile.component.js.map

/***/ }),
/* 99 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__(4);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RegisterComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var RegisterComponent = (function () {
    function RegisterComponent(authentication, router) {
        this.authentication = authentication;
        this.router = router;
        this.user = {};
        this.loading = false;
        this.invalidEmail = false;
        this.differentPassword = false;
        this.emailAlreadyExists = false;
    }
    RegisterComponent.prototype.ngOnInit = function () {
        this.user.type = 'Student';
    };
    RegisterComponent.prototype.register = function () {
        var _this = this;
        this.loading = true;
        this.emailAlreadyExists = false;
        if (this.validateEmail(this.user.email) && this.validPassword() && this.validAll()) {
            this.invalidEmail = false;
            this.differentPassword = false;
            this.authentication.register(this.user.email, this.user.password, this.user.firstName, this.user.lastName, this.user.type).subscribe(function (result) {
                _this.router.navigate(['/login']);
            }, function (error) {
                if (error.status === 406) {
                    _this.errorHandling(error);
                }
                console.log(error);
            });
        }
        else {
            if (this.user.email && this.validPassword() && this.validAll()) {
                this.invalidEmail = true;
            }
        }
        this.loading = false;
    };
    RegisterComponent.prototype.onSubmit = function () {
        this.register();
    };
    RegisterComponent.prototype.validateEmail = function (email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    };
    RegisterComponent.prototype.validPassword = function () {
        if ((this.user.password !== this.user.confirmPassword) && this.user.password && this.user.confirmPassword && this.validAll()) {
            this.differentPassword = true;
            return false;
        }
        else {
            this.differentPassword = false;
        }
        return true;
    };
    RegisterComponent.prototype.validAll = function () {
        return this.user.firstName && this.user.lastName && this.user.email;
    };
    RegisterComponent.prototype.errorHandling = function (error) {
        var p = JSON.parse(error._body);
        if (p.message === 'Email already in use') {
            this.emailAlreadyExists = true;
        }
    };
    return RegisterComponent;
}());
RegisterComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-register',
        template: __webpack_require__(386),
        styles: [__webpack_require__(355)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* Router */]) === "function" && _b || Object])
], RegisterComponent);

var _a, _b;
//# sourceMappingURL=register.component.js.map

/***/ }),
/* 100 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Answer; });
/**
 * Created by rjaf on 28/06/2017.
 */
var Answer = (function () {
    function Answer(correct, text) {
        this.correct = correct;
        this.text = text;
    }
    return Answer;
}());

//# sourceMappingURL=answer.js.map

/***/ }),
/* 101 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Notification; });
/**
 * Created by rjaf on 30/06/2017.
 */
var Notification = (function () {
    function Notification() {
    }
    return Notification;
}());

//# sourceMappingURL=notification.js.map

/***/ }),
/* 102 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Submission; });
/**
 * Created by rjaf on 01/07/2017.
 */
var Submission = (function () {
    function Submission(id, score) {
        this._id = id;
        this._score = score;
    }
    Object.defineProperty(Submission.prototype, "total", {
        get: function () {
            return this._total;
        },
        set: function (value) {
            this._total = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Submission.prototype, "correct", {
        get: function () {
            return this._correct;
        },
        set: function (value) {
            this._correct = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Submission.prototype, "id", {
        get: function () {
            return this._id;
        },
        set: function (value) {
            this._id = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Submission.prototype, "score", {
        get: function () {
            return this._score;
        },
        set: function (value) {
            this._score = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Submission.prototype, "exam", {
        get: function () {
            return this._exam;
        },
        set: function (value) {
            this._exam = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Submission.prototype, "user", {
        get: function () {
            return this._user;
        },
        set: function (value) {
            this._user = value;
        },
        enumerable: true,
        configurable: true
    });
    return Submission;
}());

//# sourceMappingURL=submission.js.map

/***/ }),
/* 103 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginGuardService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var LoginGuardService = (function () {
    function LoginGuardService(router, authentication) {
        this.router = router;
        this.authentication = authentication;
    }
    LoginGuardService.prototype.canActivate = function () {
        if (!this.authentication.isLogged()) {
            return true;
        }
        this.router.navigate(['/dashboard']);
        return false;
    };
    return LoginGuardService;
}());
LoginGuardService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__authentication_service__["a" /* AuthenticationService */]) === "function" && _b || Object])
], LoginGuardService);

var _a, _b;
//# sourceMappingURL=login-guard.service.js.map

/***/ }),
/* 104 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var UserService = (function () {
    function UserService(router, http, httpUtil, authentication) {
        this.router = router;
        this.http = http;
        this.httpUtil = httpUtil;
        this.authentication = authentication;
    }
    // GET /api/users/{user_id}
    UserService.prototype.getUserById = function (userId) {
        return this.http.get(this.httpUtil.url('/api/users/' + userId), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    UserService.prototype.updateUserById = function (user_id, firstName, lastName, password) {
        return this.http.put(this.httpUtil.url('/api/users/' + user_id), JSON.stringify({
            firstName: firstName,
            lastName: lastName,
            password: password
        }), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    return UserService;
}());
UserService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */]) === "function" && _d || Object])
], UserService);

var _a, _b, _c, _d;
//# sourceMappingURL=user.service.js.map

/***/ }),
/* 105 */,
/* 106 */,
/* 107 */,
/* 108 */,
/* 109 */,
/* 110 */,
/* 111 */,
/* 112 */,
/* 113 */,
/* 114 */,
/* 115 */,
/* 116 */,
/* 117 */,
/* 118 */,
/* 119 */,
/* 120 */,
/* 121 */,
/* 122 */,
/* 123 */,
/* 124 */,
/* 125 */,
/* 126 */,
/* 127 */,
/* 128 */,
/* 129 */,
/* 130 */,
/* 131 */,
/* 132 */,
/* 133 */,
/* 134 */,
/* 135 */,
/* 136 */,
/* 137 */,
/* 138 */,
/* 139 */,
/* 140 */,
/* 141 */,
/* 142 */,
/* 143 */,
/* 144 */,
/* 145 */,
/* 146 */,
/* 147 */,
/* 148 */,
/* 149 */,
/* 150 */,
/* 151 */,
/* 152 */,
/* 153 */,
/* 154 */,
/* 155 */,
/* 156 */,
/* 157 */,
/* 158 */,
/* 159 */,
/* 160 */,
/* 161 */,
/* 162 */,
/* 163 */,
/* 164 */,
/* 165 */,
/* 166 */,
/* 167 */,
/* 168 */,
/* 169 */,
/* 170 */,
/* 171 */,
/* 172 */,
/* 173 */,
/* 174 */,
/* 175 */,
/* 176 */,
/* 177 */,
/* 178 */,
/* 179 */,
/* 180 */,
/* 181 */,
/* 182 */,
/* 183 */,
/* 184 */,
/* 185 */,
/* 186 */,
/* 187 */,
/* 188 */,
/* 189 */,
/* 190 */,
/* 191 */,
/* 192 */,
/* 193 */,
/* 194 */,
/* 195 */,
/* 196 */,
/* 197 */,
/* 198 */,
/* 199 */,
/* 200 */,
/* 201 */,
/* 202 */,
/* 203 */,
/* 204 */,
/* 205 */,
/* 206 */,
/* 207 */,
/* 208 */,
/* 209 */,
/* 210 */,
/* 211 */,
/* 212 */,
/* 213 */,
/* 214 */,
/* 215 */,
/* 216 */,
/* 217 */,
/* 218 */,
/* 219 */,
/* 220 */,
/* 221 */,
/* 222 */,
/* 223 */,
/* 224 */,
/* 225 */,
/* 226 */,
/* 227 */,
/* 228 */,
/* 229 */,
/* 230 */,
/* 231 */,
/* 232 */,
/* 233 */,
/* 234 */,
/* 235 */,
/* 236 */,
/* 237 */,
/* 238 */,
/* 239 */,
/* 240 */,
/* 241 */,
/* 242 */,
/* 243 */,
/* 244 */,
/* 245 */,
/* 246 */,
/* 247 */
/***/ (function(module, exports) {

function webpackEmptyContext(req) {
	throw new Error("Cannot find module '" + req + "'.");
}
webpackEmptyContext.keys = function() { return []; };
webpackEmptyContext.resolve = webpackEmptyContext;
module.exports = webpackEmptyContext;
webpackEmptyContext.id = 247;


/***/ }),
/* 248 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__(253);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__(255);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__(270);




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["enableProdMode"])();
}
__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),
/* 249 */,
/* 250 */,
/* 251 */,
/* 252 */,
/* 253 */,
/* 254 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var AppComponent = (function () {
    function AppComponent(authentication) {
        this.authentication = authentication;
    }
    AppComponent.prototype.isLogged = function () {
        return false; // this.authentication.isLogged();
    };
    AppComponent.prototype.logout = function () {
        this.authentication.logout();
    };
    AppComponent.prototype.isTeacher = function () {
        return localStorage['currentUser'].isTeacher();
    };
    AppComponent.prototype.getUserName = function () {
        return this.authentication.getUserName();
    };
    return AppComponent;
}());
AppComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-root',
        template: __webpack_require__(358),
        styles: [__webpack_require__(327)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object])
], AppComponent);

var _a;
//# sourceMappingURL=app.component.js.map

/***/ }),
/* 255 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(32);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(252);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app_component__ = __webpack_require__(254);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__components_login_login_component__ = __webpack_require__(96);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__components_register_register_component__ = __webpack_require__(99);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__app_routes__ = __webpack_require__(256);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__components_home_home_component__ = __webpack_require__(95);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__services_login_guard_service__ = __webpack_require__(103);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__components_dashboard_dashboard_component__ = __webpack_require__(91);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__services_http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__components_dashboard_default_default_component__ = __webpack_require__(92);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__services_categories_service__ = __webpack_require__(52);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__services_classes_service__ = __webpack_require__(41);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__services_invitations_service__ = __webpack_require__(269);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__services_notification_service__ = __webpack_require__(53);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__services_questions_service__ = __webpack_require__(34);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19__services_students_service__ = __webpack_require__(54);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20__services_user_service__ = __webpack_require__(104);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21__services_group_service__ = __webpack_require__(31);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22__components_dashboard_default_list_class_list_class_component__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_23__components_dashboard_default_list_exame_list_exame_component__ = __webpack_require__(264);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_24__components_dashboard_class_class_component__ = __webpack_require__(80);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25__components_dashboard_class_groups_groups_component__ = __webpack_require__(260);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_26__components_dashboard_schedule_schedule_component__ = __webpack_require__(94);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_27_ap_angular2_fullcalendar__ = __webpack_require__(105);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_27_ap_angular2_fullcalendar___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_27_ap_angular2_fullcalendar__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_28__services_breadcrumb_service__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_29__components_dashboard_class_groups_group_create_group_create_component__ = __webpack_require__(86);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_30__components_dashboard_results_results_component__ = __webpack_require__(93);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_31__components_dashboard_class_groups_group_view_group_view_component__ = __webpack_require__(88);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_32__services_exams_service__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_33__components_dashboard_class_exams_exam_create_question_add_question_add_component__ = __webpack_require__(257);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_34__components_dashboard_class_exams_exam_create_question_preview_question_preview_component__ = __webpack_require__(258);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_35__execption_exception__ = __webpack_require__(19);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_36__components_dashboard_class_questions_question_create_question_create_component__ = __webpack_require__(89);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_37__components_dashboard_class_questions_questions_component__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_38__components_dashboard_class_questions_question_create_question_create_item_question_create_item_component__ = __webpack_require__(262);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_39__components_dashboard_class_questions_question_create_question_create_item_answer_create_answer_create_component__ = __webpack_require__(261);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_40__components_dashboard_class_exams_exams_component__ = __webpack_require__(85);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_41_app_components_dashboard_class_exams_exam_create_exam_create_component__ = __webpack_require__(81);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_42__filters_students_filter__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_43__filters_students_filter_group__ = __webpack_require__(266);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_44__components_notification_notification_component__ = __webpack_require__(97);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_45__components_dashboard_class_exams_exam_submission_exam_submission_component__ = __webpack_require__(84);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_46__components_dashboard_class_exams_exam_submission_question_submit_question_submit_component__ = __webpack_require__(259);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_47__components_dashboard_class_exams_exam_result_exam_result_component__ = __webpack_require__(83);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_48__components_dashboard_class_exams_exam_result_all_exam_result_all_component__ = __webpack_require__(82);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_49__components_profile_profile_component__ = __webpack_require__(98);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_50__services_scores_service__ = __webpack_require__(42);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_51__components_dashboard_class_groups_group_score_group_score_component__ = __webpack_require__(87);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_52__services_navbar_service__ = __webpack_require__(25);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_53__filters_students_filter_group_score__ = __webpack_require__(267);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_54__filters_students_filter_result_all__ = __webpack_require__(268);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};























































var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["NgModule"])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* AppComponent */],
            __WEBPACK_IMPORTED_MODULE_5__components_login_login_component__["a" /* LoginComponent */],
            __WEBPACK_IMPORTED_MODULE_6__components_register_register_component__["a" /* RegisterComponent */],
            __WEBPACK_IMPORTED_MODULE_9__components_home_home_component__["a" /* HomeComponent */],
            __WEBPACK_IMPORTED_MODULE_11__components_dashboard_dashboard_component__["a" /* DashboardComponent */],
            __WEBPACK_IMPORTED_MODULE_13__components_dashboard_default_default_component__["a" /* DefaultComponent */],
            __WEBPACK_IMPORTED_MODULE_22__components_dashboard_default_list_class_list_class_component__["a" /* ListClassComponent */],
            __WEBPACK_IMPORTED_MODULE_23__components_dashboard_default_list_exame_list_exame_component__["a" /* ListExameComponent */],
            __WEBPACK_IMPORTED_MODULE_24__components_dashboard_class_class_component__["a" /* ClassComponent */],
            __WEBPACK_IMPORTED_MODULE_25__components_dashboard_class_groups_groups_component__["a" /* GroupsComponent */],
            __WEBPACK_IMPORTED_MODULE_26__components_dashboard_schedule_schedule_component__["a" /* ScheduleComponent */],
            __WEBPACK_IMPORTED_MODULE_27_ap_angular2_fullcalendar__["CalendarComponent"],
            __WEBPACK_IMPORTED_MODULE_29__components_dashboard_class_groups_group_create_group_create_component__["a" /* GroupCreateComponent */],
            __WEBPACK_IMPORTED_MODULE_30__components_dashboard_results_results_component__["a" /* ResultsComponent */],
            __WEBPACK_IMPORTED_MODULE_31__components_dashboard_class_groups_group_view_group_view_component__["a" /* GroupViewComponent */],
            __WEBPACK_IMPORTED_MODULE_41_app_components_dashboard_class_exams_exam_create_exam_create_component__["a" /* ExamCreateComponent */],
            __WEBPACK_IMPORTED_MODULE_33__components_dashboard_class_exams_exam_create_question_add_question_add_component__["a" /* QuestionAddComponent */],
            __WEBPACK_IMPORTED_MODULE_34__components_dashboard_class_exams_exam_create_question_preview_question_preview_component__["a" /* QuestionPreviewComponent */],
            __WEBPACK_IMPORTED_MODULE_36__components_dashboard_class_questions_question_create_question_create_component__["a" /* QuestionCreateComponent */],
            __WEBPACK_IMPORTED_MODULE_37__components_dashboard_class_questions_questions_component__["a" /* QuestionsComponent */],
            __WEBPACK_IMPORTED_MODULE_38__components_dashboard_class_questions_question_create_question_create_item_question_create_item_component__["a" /* QuestionCreateItemComponent */],
            __WEBPACK_IMPORTED_MODULE_39__components_dashboard_class_questions_question_create_question_create_item_answer_create_answer_create_component__["a" /* AnswerCreateComponent */],
            __WEBPACK_IMPORTED_MODULE_40__components_dashboard_class_exams_exams_component__["a" /* ExamsComponent */],
            __WEBPACK_IMPORTED_MODULE_42__filters_students_filter__["a" /* StudentsFilter */],
            __WEBPACK_IMPORTED_MODULE_43__filters_students_filter_group__["a" /* StudentsFilterGroup */],
            __WEBPACK_IMPORTED_MODULE_44__components_notification_notification_component__["a" /* NotificationComponent */],
            __WEBPACK_IMPORTED_MODULE_45__components_dashboard_class_exams_exam_submission_exam_submission_component__["a" /* ExamSubmissionComponent */],
            __WEBPACK_IMPORTED_MODULE_46__components_dashboard_class_exams_exam_submission_question_submit_question_submit_component__["a" /* QuestionSubmitComponent */],
            __WEBPACK_IMPORTED_MODULE_47__components_dashboard_class_exams_exam_result_exam_result_component__["a" /* ExamResultComponent */],
            __WEBPACK_IMPORTED_MODULE_48__components_dashboard_class_exams_exam_result_all_exam_result_all_component__["a" /* ExamResultAllComponent */],
            __WEBPACK_IMPORTED_MODULE_49__components_profile_profile_component__["a" /* ProfileComponent */],
            __WEBPACK_IMPORTED_MODULE_51__components_dashboard_class_groups_group_score_group_score_component__["a" /* GroupScoreComponent */],
            __WEBPACK_IMPORTED_MODULE_53__filters_students_filter_group_score__["a" /* StudentsFilterGroupScore */],
            __WEBPACK_IMPORTED_MODULE_54__filters_students_filter_result_all__["a" /* StudentsFilterResultAll */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
            __WEBPACK_IMPORTED_MODULE_3__angular_http__["a" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_7__app_routes__["a" /* routing */]
            // PopupModule.forRoot()
        ],
        providers: [
            __WEBPACK_IMPORTED_MODULE_12__services_http_util_service__["a" /* HttpUtilService */],
            __WEBPACK_IMPORTED_MODULE_8__services_authentication_service__["a" /* AuthenticationService */],
            __WEBPACK_IMPORTED_MODULE_10__services_login_guard_service__["a" /* LoginGuardService */],
            __WEBPACK_IMPORTED_MODULE_14__services_categories_service__["a" /* CategoriesService */],
            __WEBPACK_IMPORTED_MODULE_15__services_classes_service__["a" /* ClassesService */],
            __WEBPACK_IMPORTED_MODULE_21__services_group_service__["a" /* GroupService */],
            __WEBPACK_IMPORTED_MODULE_16__services_invitations_service__["a" /* InvitationsService */],
            __WEBPACK_IMPORTED_MODULE_17__services_notification_service__["a" /* NotificationService */],
            __WEBPACK_IMPORTED_MODULE_18__services_questions_service__["a" /* QuestionsService */],
            __WEBPACK_IMPORTED_MODULE_19__services_students_service__["a" /* StudentsService */],
            __WEBPACK_IMPORTED_MODULE_20__services_user_service__["a" /* UserService */],
            __WEBPACK_IMPORTED_MODULE_28__services_breadcrumb_service__["a" /* BreadCrumbService */],
            __WEBPACK_IMPORTED_MODULE_32__services_exams_service__["a" /* ExamsService */],
            __WEBPACK_IMPORTED_MODULE_35__execption_exception__["a" /* Exception */],
            __WEBPACK_IMPORTED_MODULE_50__services_scores_service__["a" /* ScoresService */],
            __WEBPACK_IMPORTED_MODULE_52__services_navbar_service__["a" /* NavbarService */]
        ],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* AppComponent */]]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),
/* 256 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__components_login_login_component__ = __webpack_require__(96);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__components_register_register_component__ = __webpack_require__(99);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__components_home_home_component__ = __webpack_require__(95);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_login_guard_service__ = __webpack_require__(103);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__components_dashboard_dashboard_component__ = __webpack_require__(91);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__components_dashboard_default_default_component__ = __webpack_require__(92);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__components_dashboard_class_class_component__ = __webpack_require__(80);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__components_dashboard_schedule_schedule_component__ = __webpack_require__(94);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__components_dashboard_class_groups_group_create_group_create_component__ = __webpack_require__(86);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__components_dashboard_results_results_component__ = __webpack_require__(93);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__components_dashboard_class_exams_exam_create_exam_create_component__ = __webpack_require__(81);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__components_dashboard_class_groups_group_view_group_view_component__ = __webpack_require__(88);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__components_dashboard_class_questions_questions_component__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__components_dashboard_class_questions_question_create_question_create_component__ = __webpack_require__(89);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15_app_components_dashboard_class_exams_exams_component__ = __webpack_require__(85);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__components_notification_notification_component__ = __webpack_require__(97);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__components_dashboard_class_exams_exam_submission_exam_submission_component__ = __webpack_require__(84);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__components_dashboard_class_exams_exam_result_exam_result_component__ = __webpack_require__(83);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19__components_dashboard_class_exams_exam_result_all_exam_result_all_component__ = __webpack_require__(82);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20__components_profile_profile_component__ = __webpack_require__(98);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21__components_dashboard_class_groups_group_score_group_score_component__ = __webpack_require__(87);
/* unused harmony export routes */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return routing; });
/**
 * Created by rjaf on 30/05/2017.
 */






















var routes = [
    { path: '', component: __WEBPACK_IMPORTED_MODULE_3__components_home_home_component__["a" /* HomeComponent */], pathMatch: 'full', canActivate: [__WEBPACK_IMPORTED_MODULE_4__services_login_guard_service__["a" /* LoginGuardService */]] },
    { path: 'login', component: __WEBPACK_IMPORTED_MODULE_1__components_login_login_component__["a" /* LoginComponent */], canActivate: [__WEBPACK_IMPORTED_MODULE_4__services_login_guard_service__["a" /* LoginGuardService */]] },
    { path: 'register', component: __WEBPACK_IMPORTED_MODULE_2__components_register_register_component__["a" /* RegisterComponent */], canActivate: [__WEBPACK_IMPORTED_MODULE_4__services_login_guard_service__["a" /* LoginGuardService */]] },
    { path: 'dashboard', component: __WEBPACK_IMPORTED_MODULE_5__components_dashboard_dashboard_component__["a" /* DashboardComponent */],
        children: [
            { path: '', component: __WEBPACK_IMPORTED_MODULE_6__components_dashboard_default_default_component__["a" /* DefaultComponent */] },
            { path: 'classes/:class_id/groups/:group_id/exams/new', component: __WEBPACK_IMPORTED_MODULE_11__components_dashboard_class_exams_exam_create_exam_create_component__["a" /* ExamCreateComponent */] },
            { path: 'classes/:class_id/groups/:group_id/exams/:exam_id', component: __WEBPACK_IMPORTED_MODULE_15_app_components_dashboard_class_exams_exams_component__["a" /* ExamsComponent */] },
            { path: 'classes/:class_id/groups/:group_id/exams/:exam_id/submit', component: __WEBPACK_IMPORTED_MODULE_17__components_dashboard_class_exams_exam_submission_exam_submission_component__["a" /* ExamSubmissionComponent */] },
            { path: 'classes/:class_id/groups/:group_id/exams/:exam_id/submission/:submission_id', component: __WEBPACK_IMPORTED_MODULE_18__components_dashboard_class_exams_exam_result_exam_result_component__["a" /* ExamResultComponent */] },
            { path: 'classes/:class_id/groups/:group_id/exams/:exam_id/results', component: __WEBPACK_IMPORTED_MODULE_19__components_dashboard_class_exams_exam_result_all_exam_result_all_component__["a" /* ExamResultAllComponent */] },
            { path: 'classes/:class_id/groups/:group_id/results', component: __WEBPACK_IMPORTED_MODULE_21__components_dashboard_class_groups_group_score_group_score_component__["a" /* GroupScoreComponent */] },
            { path: 'classes/:class_id/questions/new', component: __WEBPACK_IMPORTED_MODULE_14__components_dashboard_class_questions_question_create_question_create_component__["a" /* QuestionCreateComponent */] },
            { path: 'classes/:class_id/questions', component: __WEBPACK_IMPORTED_MODULE_13__components_dashboard_class_questions_questions_component__["a" /* QuestionsComponent */] },
            { path: 'classes/:class_id', component: __WEBPACK_IMPORTED_MODULE_7__components_dashboard_class_class_component__["a" /* ClassComponent */],
                children: [
                    { path: 'groups/new', component: __WEBPACK_IMPORTED_MODULE_9__components_dashboard_class_groups_group_create_group_create_component__["a" /* GroupCreateComponent */] },
                    { path: 'groups/:group_id', component: __WEBPACK_IMPORTED_MODULE_12__components_dashboard_class_groups_group_view_group_view_component__["a" /* GroupViewComponent */] },
                ] },
            { path: 'schedule', component: __WEBPACK_IMPORTED_MODULE_8__components_dashboard_schedule_schedule_component__["a" /* ScheduleComponent */] },
            { path: 'results', component: __WEBPACK_IMPORTED_MODULE_10__components_dashboard_results_results_component__["a" /* ResultsComponent */] },
            { path: 'notifications', component: __WEBPACK_IMPORTED_MODULE_16__components_notification_notification_component__["a" /* NotificationComponent */] },
            { path: 'profile', component: __WEBPACK_IMPORTED_MODULE_20__components_profile_profile_component__["a" /* ProfileComponent */] }
        ]
    }
];
var routing = __WEBPACK_IMPORTED_MODULE_0__angular_router__["b" /* RouterModule */].forRoot(routes);
//# sourceMappingURL=app.routes.js.map

/***/ }),
/* 257 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__models_question__ = __webpack_require__(30);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_questions_service__ = __webpack_require__(34);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionAddComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
///<reference path="../../../../../../../../node_modules/@angular/core/src/metadata/lifecycle_hooks.d.ts"/>



var QuestionAddComponent = (function () {
    function QuestionAddComponent(quest) {
        this.quest = quest;
        this.removeQuestion = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.removeFromAllQuestionsAvailable = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.changeNow = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.questionModel = {};
        this.dificultys = [];
    }
    QuestionAddComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.generateAll = false;
        this.lastSaveAll = this.saveAll;
        this.display_details = false;
        this.toogle_display_details = false;
        this.questionModel.dificult = '-1';
        if (this.categories.length === 0) {
            setTimeout(function () {
                _this.questionModel.category = _this.categories[0];
                _this.selectDificultyAvailable();
            }, 1000);
        }
    };
    QuestionAddComponent.prototype.ngOnChanges = function (changes) {
        if (this.question.dificulty + '' === '-1') {
            this.display_details = false;
            this.generateAll = false;
            this.lastSaveAll = this.saveAll;
            this.toogle_display_details = false;
            this.questionModel.category = this.categories[0];
            this.questionModel.dificult = '-1';
            this.selectDificultyAvailable();
        }
        else {
            if (this.lastSaveAll !== this.saveAll) {
                this.lastSaveAll = this.saveAll;
                this.question.category = this.questionModel.category;
                this.question.dificulty = this.questionModel.dificult;
            }
            else {
                if (this.generateAll) {
                    this.generateQuestion();
                    this.generateAll = false;
                }
            }
        }
    };
    QuestionAddComponent.prototype.selectDificultyAvailable = function () {
        var _this = this;
        setTimeout(function () {
            var categ = _this.questionModel.category;
            _this.dificultys = [];
            for (var dific in _this.allQuestionsAvailable[categ]) {
                if (+dific === 1 && !_this.dificultys.find(function (x) { return x.value === 1; })) {
                    if (_this.allQuestionsAvailable[categ][1].available > 0) {
                        _this.dificultys.push({ value: 1, dificulty: 'Easy' });
                    }
                }
                if (+dific === 2 && !_this.dificultys.find(function (x) { return x.value === 2; })) {
                    if (_this.allQuestionsAvailable[categ][2].available > 0) {
                        _this.dificultys.push({ value: 2, dificulty: 'Normal' });
                    }
                }
                if (+dific === 3 && !_this.dificultys.find(function (x) { return x.value === 3; })) {
                    if (_this.allQuestionsAvailable[categ][3].available > 0) {
                        _this.dificultys.push({ value: 3, dificulty: 'Hard' });
                    }
                }
            }
            _this.questionModel.dificult = '-1';
            /* if ( this.dificultys.length > 0 ) {
               console.log(this.dificultys[0].value);
               this.questionModel.dificult = this.dificultys[0].value;
             } else {
               this.questionModel.dificult = '';
             }*/
        }, 1);
    };
    QuestionAddComponent.prototype.toggleDiplay = function () {
        this.toogle_display_details = !this.toogle_display_details;
    };
    QuestionAddComponent.prototype.removeQuestionNow = function () {
        if (this.question.id) {
            // TODO fazer o metodo que volta a colocar lá essa questão que gerou visto que é a segunda vez que ele gera.
            var questi = this.question;
            this.removeFromGenerate(questi);
        }
        if (this.questionModel.dificult !== '-1') {
            this.allQuestionsAvailable[this.questionModel.category][this.questionModel.dificult].available = this.allQuestionsAvailable[this.questionModel.category][this.questionModel.dificult].available + 1;
        }
        this.question.dificulty = this.questionModel.dificult;
        this.question.category = this.questionModel.category;
        this.removeQuestion.emit(this.question);
    };
    QuestionAddComponent.prototype.generateQuestion = function () {
        var _this = this;
        this.question.dificulty = this.questionModel.dificult;
        if (this.questionModel.dificult !== '-1') {
            if (this.question.id) {
                // TODO fazer o metodo que volta a colocar lá essa questão que gerou visto que é a segunda vez que ele gera.
                var questi = this.question;
                this.removeFromGenerate(questi);
            }
            var allExclude = [];
            if (this.allGenerateNow.get(this.questionModel.category)) {
                if (this.allGenerateNow.get(this.questionModel.category).get(+this.questionModel.dificult)) {
                    allExclude = this.allGenerateNow.get(this.questionModel.category).get(+this.questionModel.dificult);
                }
            }
            this.quest.createQuestionByGenerate(this.groupId, this.questionModel.category, this.questionModel.dificult, allExclude).subscribe(function (resultado) {
                _this.question.category = resultado.category;
                _this.question.dificulty = resultado.difficulty;
                _this.question.text = resultado.text;
                _this.question.id = resultado.id;
                _this.question.answers = resultado.answers;
                // this.removeFromallQuestionsAvailable();
                _this.removeFromAllQuestionsAvailable.emit(_this.question.id);
            }, function (error) {
                console.log(error);
            });
        }
    };
    QuestionAddComponent.prototype.removeFromallQuestionsAvailables = function () {
        if (this.questionModel.dificult !== '-1' && !this.question.id) {
            var p = this.questionModel.dificult;
            this.allQuestionsAvailable[this.questionModel.category][p].available = this.allQuestionsAvailable[this.questionModel.category][p].available - 1;
            this.changeNow.emit();
        }
    };
    QuestionAddComponent.prototype.removeFromGenerate = function (question) {
        var difi = question.dificulty;
        var cate = question.category;
        var id = question.id;
        var p = question.dificulty;
        if (this.allGenerateNow.get(cate).get(+difi)) {
            var indexArray = this.allGenerateNow.get(cate).get(+difi).indexOf(id);
            this.allGenerateNow.get(cate).get(+difi).splice(indexArray, 1);
            this.allQuestionsAvailable[cate][p].available = this.allQuestionsAvailable[cate][p].available + 1;
            this.changeNow.emit();
        }
    };
    return QuestionAddComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */]) === "function" && _a || Object)
], QuestionAddComponent.prototype, "question", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Array)
], QuestionAddComponent.prototype, "categories", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Object)
], QuestionAddComponent.prototype, "allQuestionsAvailable", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Number)
], QuestionAddComponent.prototype, "groupId", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Boolean)
], QuestionAddComponent.prototype, "updateDificulty", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Object)
], QuestionAddComponent.prototype, "allGenerateNow", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Boolean)
], QuestionAddComponent.prototype, "saveAll", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Boolean)
], QuestionAddComponent.prototype, "generateAll", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])(),
    __metadata("design:type", Object)
], QuestionAddComponent.prototype, "removeQuestion", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])(),
    __metadata("design:type", Object)
], QuestionAddComponent.prototype, "removeFromAllQuestionsAvailable", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])(),
    __metadata("design:type", Object)
], QuestionAddComponent.prototype, "changeNow", void 0);
QuestionAddComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-question-add',
        template: __webpack_require__(361),
        styles: [__webpack_require__(330)]
    }),
    __metadata("design:paramtypes", [typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_questions_service__["a" /* QuestionsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_questions_service__["a" /* QuestionsService */]) === "function" && _b || Object])
], QuestionAddComponent);

var _a, _b;
//# sourceMappingURL=question-add.component.js.map

/***/ }),
/* 258 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_app_models_question__ = __webpack_require__(30);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionPreviewComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var QuestionPreviewComponent = (function () {
    function QuestionPreviewComponent() {
    }
    QuestionPreviewComponent.prototype.ngOnInit = function () {
    };
    QuestionPreviewComponent.prototype.getDifficulty = function () {
        if (this.question.dificulty === 1) {
            return 'Easy';
        }
        else {
            if (this.question.dificulty === 2) {
                return 'Normal';
            }
            return 'Hard';
        }
    };
    return QuestionPreviewComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1_app_models_question__["a" /* Question */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1_app_models_question__["a" /* Question */]) === "function" && _a || Object)
], QuestionPreviewComponent.prototype, "question", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Number)
], QuestionPreviewComponent.prototype, "choiseanswersId", void 0);
QuestionPreviewComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-question-preview',
        template: __webpack_require__(362),
        styles: [__webpack_require__(331)]
    }),
    __metadata("design:paramtypes", [])
], QuestionPreviewComponent);

var _a;
//# sourceMappingURL=question-preview.component.js.map

/***/ }),
/* 259 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_exams_service__ = __webpack_require__(15);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionSubmitComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var QuestionSubmitComponent = (function () {
    function QuestionSubmitComponent(examService) {
        this.examService = examService;
        this.saveOk = false;
        this.oldsaveAll = false;
        this.correctAnswer = {};
    }
    QuestionSubmitComponent.prototype.ngOnInit = function () {
        this.oldsaveAll = this.saveAll;
        if (this.choiseanswersId !== -1) {
            this.correctAnswer.correta = this.choiseanswersId;
        }
        else {
            this.correctAnswer.correta = -1;
        }
    };
    QuestionSubmitComponent.prototype.ngOnChanges = function (changes) {
        if (this.oldsaveAll !== this.saveAll) {
            this.oldsaveAll = this.saveAll;
            this.saveAnswerNow();
        }
    };
    QuestionSubmitComponent.prototype.saveAnswerNow = function () {
        var _this = this;
        if (this.correctAnswer.correta !== -1) {
            if (this.correctAnswer.correta !== this.choiseanswersId) {
                this.choiseanswersId = this.correctAnswer.correta;
                this.examService.putExamAnswerSubmission(this.submissionId, this.question.id, this.correctAnswer.correta).subscribe(function (result) {
                    _this.saveOk = true;
                }, function (error) {
                    console.log(error);
                });
            }
            this.saveOk = true;
        }
    };
    QuestionSubmitComponent.prototype.getname = function () {
        return 'name' + this.question.id;
    };
    return QuestionSubmitComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Object)
], QuestionSubmitComponent.prototype, "question", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Number)
], QuestionSubmitComponent.prototype, "submissionId", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Number)
], QuestionSubmitComponent.prototype, "choiseanswersId", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Boolean)
], QuestionSubmitComponent.prototype, "saveAll", void 0);
QuestionSubmitComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-question-submit',
        template: __webpack_require__(366),
        styles: [__webpack_require__(335)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_exams_service__["a" /* ExamsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_exams_service__["a" /* ExamsService */]) === "function" && _a || Object])
], QuestionSubmitComponent);

var _a;
//# sourceMappingURL=question-submit.component.js.map

/***/ }),
/* 260 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GroupsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var GroupsComponent = (function () {
    function GroupsComponent(authentication) {
        this.authentication = authentication;
    }
    GroupsComponent.prototype.ngOnInit = function () {
    };
    GroupsComponent.prototype.ngAfterViewInit = function () {
    };
    GroupsComponent.prototype.isTeacher = function () {
        return this.authentication.isTeacher();
    };
    return GroupsComponent;
}());
GroupsComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-groups',
        template: __webpack_require__(371),
        styles: [__webpack_require__(340)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object])
], GroupsComponent);

var _a;
//# sourceMappingURL=groups.component.js.map

/***/ }),
/* 261 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__models_answer__ = __webpack_require__(100);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AnswerCreateComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var AnswerCreateComponent = (function () {
    function AnswerCreateComponent() {
        this.clickRadio = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.removeAnswer = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.answe = {};
    }
    AnswerCreateComponent.prototype.ngOnInit = function () {
        this.oldsaveanswer = this.saveanswer;
    };
    AnswerCreateComponent.prototype.ngOnChanges = function (changes) {
        this.answer.text = this.answe.answerText;
        this.oldViewRemoveAnswer = this.viewRemoveAnswer;
    };
    AnswerCreateComponent.prototype.saveAnswer = function () {
        this.answer.text = this.answe.answerText;
    };
    AnswerCreateComponent.prototype.getValue = function () {
        this.clickRadio.emit(this.i);
    };
    AnswerCreateComponent.prototype.removeAnserFromQuestion = function () {
        this.removeAnswer.emit(this.answer);
    };
    return AnswerCreateComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__models_answer__["a" /* Answer */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__models_answer__["a" /* Answer */]) === "function" && _a || Object)
], AnswerCreateComponent.prototype, "answer", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Boolean)
], AnswerCreateComponent.prototype, "saveanswer", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Number)
], AnswerCreateComponent.prototype, "i", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Boolean)
], AnswerCreateComponent.prototype, "changeSave", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Boolean)
], AnswerCreateComponent.prototype, "viewRemoveAnswer", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])(),
    __metadata("design:type", Object)
], AnswerCreateComponent.prototype, "clickRadio", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])(),
    __metadata("design:type", Object)
], AnswerCreateComponent.prototype, "removeAnswer", void 0);
AnswerCreateComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-answer-create',
        template: __webpack_require__(372),
        styles: [__webpack_require__(341)]
    }),
    __metadata("design:paramtypes", [])
], AnswerCreateComponent);

var _a;
//# sourceMappingURL=answer-create.component.js.map

/***/ }),
/* 262 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__models_question__ = __webpack_require__(30);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__models_answer__ = __webpack_require__(100);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_questions_service__ = __webpack_require__(34);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionCreateItemComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var QuestionCreateItemComponent = (function () {
    function QuestionCreateItemComponent(questionService) {
        this.questionService = questionService;
        this.saveQuestionButton = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.questionPostOK = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.removeQuestio = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
        this.quest = {};
    }
    QuestionCreateItemComponent.prototype.ngOnInit = function () {
        this.quest.dificulty = __WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */]._Easy;
        this.answers = [];
        this.saveanswer = false;
        this.changeSave = false;
        this.needSelectRadio = false;
        this.removeAnswer = false;
        this.correctAnswerIndex = -1;
        this.oldsaveAll = this.saveAll;
        this.answers.push(new __WEBPACK_IMPORTED_MODULE_2__models_answer__["a" /* Answer */](false, ''));
        this.answers.push(new __WEBPACK_IMPORTED_MODULE_2__models_answer__["a" /* Answer */](false, ''));
    };
    QuestionCreateItemComponent.prototype.ngOnChanges = function (changes) {
        if (this.saveAll !== this.oldsaveAll) {
            this.oldsaveAll = this.saveAll;
            this.needSelectRadio = false;
            if (this.saveAll === true) {
                this.saveQuestion();
            }
        }
        else {
            this.needSelectRadio = false;
        }
    };
    QuestionCreateItemComponent.prototype.viewRemoveAnswer = function () {
        if (this.answers.length > 2) {
            this.removeAnswer = true;
            return true;
        }
        this.removeAnswer = false;
        return false;
    };
    QuestionCreateItemComponent.prototype.addAnswer = function () {
        this.answers.push(new __WEBPACK_IMPORTED_MODULE_2__models_answer__["a" /* Answer */](false, ''));
        if (this.answers.length > 2) {
            this.removeAnswer = true;
        }
        else {
            this.removeAnswer = false;
        }
    };
    QuestionCreateItemComponent.prototype.saveQuestion = function () {
        this.saveanswer = true;
        if (this.correctAnswerIndex !== -1) {
            this.needSelectRadio = false;
            if (this.changeSave === true) {
                this.changeSave = false;
            }
            else {
                this.changeSave = true;
            }
            this.question.category = this.quest.category;
            this.question.dificulty = this.quest.dificulty;
            this.question.text = this.quest.questionText;
            this.question.answers = [];
            this.checkAnswer();
        }
        else {
            this.needSelectRadio = true;
        }
    };
    QuestionCreateItemComponent.prototype.checkAnswer = function () {
        var _this = this;
        setTimeout(function () {
            var allOk = true;
            var index = 0;
            for (var _i = 0, _a = _this.answers; _i < _a.length; _i++) {
                var answer = _a[_i];
                if (!answer.text) {
                    allOk = false;
                    break;
                }
                if (_this.correctAnswerIndex === index) {
                    answer.correct = true;
                }
                else {
                    answer.correct = false;
                }
                _this.question.answers.push(answer);
                index++;
            }
            if (allOk) {
                _this.addQuestion();
            }
        }, 1);
    };
    QuestionCreateItemComponent.prototype.addQuestion = function () {
        var _this = this;
        this.questionService.createQuestionByClasse(this.classId, this.question.text, this.question.category, this.question.dificulty, this.question.answers).subscribe(function (resultado) {
            if (_this.saveAll === true) {
                _this.questionPostOK.emit(_this.question);
            }
            else {
                _this.saveQuestionButton.emit(_this.question);
            }
        }, function (error) {
            console.log(error);
        });
    };
    QuestionCreateItemComponent.prototype.correctAnswer = function ($event) {
        this.correctAnswerIndex = $event;
    };
    QuestionCreateItemComponent.prototype.removeAnswerFromQuestion = function ($event) {
        var indexanswer = this.answers.indexOf($event);
        this.answers.splice(indexanswer, 1);
        this.viewRemoveAnswer();
    };
    QuestionCreateItemComponent.prototype.removeQuestion = function () {
        this.removeQuestio.emit(this.question);
    };
    return QuestionCreateItemComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */]) === "function" && _a || Object)
], QuestionCreateItemComponent.prototype, "question", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Array)
], QuestionCreateItemComponent.prototype, "allCategories", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Boolean)
], QuestionCreateItemComponent.prototype, "saveAll", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Number)
], QuestionCreateItemComponent.prototype, "classId", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])(),
    __metadata("design:type", Object)
], QuestionCreateItemComponent.prototype, "saveQuestionButton", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])(),
    __metadata("design:type", Object)
], QuestionCreateItemComponent.prototype, "questionPostOK", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])(),
    __metadata("design:type", Object)
], QuestionCreateItemComponent.prototype, "removeQuestio", void 0);
QuestionCreateItemComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-question-create-item',
        template: __webpack_require__(373),
        styles: [__webpack_require__(342)]
    }),
    __metadata("design:paramtypes", [typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__services_questions_service__["a" /* QuestionsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__services_questions_service__["a" /* QuestionsService */]) === "function" && _b || Object])
], QuestionCreateItemComponent);

var _a, _b;
//# sourceMappingURL=question-create-item.component.js.map

/***/ }),
/* 263 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_group_service__ = __webpack_require__(31);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__models_group__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_class__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__models_user__ = __webpack_require__(14);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__execption_exception__ = __webpack_require__(19);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__services_navbar_service__ = __webpack_require__(25);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ListClassComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








var ListClassComponent = (function () {
    function ListClassComponent(groupsService, authentication, exception, navbarService) {
        this.groupsService = groupsService;
        this.authentication = authentication;
        this.exception = exception;
        this.navbarService = navbarService;
        this.allGroups = new Array();
    }
    ListClassComponent.prototype.ngOnInit = function () {
        this.getAllGroups();
    };
    ListClassComponent.prototype.ngAfterViewInit = function () {
        // onReady();
    };
    ListClassComponent.prototype.rezise = function () {
        x_navigation();
        page_content_onresize();
    };
    ListClassComponent.prototype.getAllGroups = function () {
        var _this = this;
        this.groupsService.getGroupByUser(this.authentication.getUserId()).subscribe(function (resultado) {
            for (var _i = 0, resultado_1 = resultado; _i < resultado_1.length; _i++) {
                var group = resultado_1[_i];
                _this.allGroups.push(_this.createGroup(group));
            }
            _this.rezise();
        }, function (error) {
            console.log(error);
        });
    };
    ListClassComponent.prototype.createGroup = function (groupT) {
        var teacher = groupT._class.teacher;
        var _teacher = new __WEBPACK_IMPORTED_MODULE_5__models_user__["a" /* User */](teacher.id, teacher.email, teacher.firstName, teacher.lastName, teacher.type, '');
        var classe = groupT._class;
        var _classe = new __WEBPACK_IMPORTED_MODULE_4__models_class__["a" /* Class */](classe.name, classe.abbreviation);
        _classe.id = classe.id;
        _classe.user = _teacher;
        var group = groupT;
        var _group = new __WEBPACK_IMPORTED_MODULE_3__models_group__["a" /* Group */](group.name);
        _group.id = group.id;
        _group.class = _classe;
        return _group;
    };
    ListClassComponent.prototype.deleteGroup = function (group_id) {
        var _this = this;
        this.groupsService.deleteGroupById(group_id).subscribe(function (result) {
            _this.navbarService.sendUpdate(true);
            _this.allGroups = _this.allGroups.filter(function (obj) { return obj.id !== group_id; });
        }, function (error) {
            console.log(error);
            _this.exception.errorHandlingInvalidToken(error);
        });
        // TODO fazer delete do group
    };
    ListClassComponent.prototype.editGroup = function (group_id) {
        // TODO fazer edit do group
    };
    return ListClassComponent;
}());
ListClassComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-list-class',
        template: __webpack_require__(378),
        styles: [__webpack_require__(347)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_group_service__["a" /* GroupService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_group_service__["a" /* GroupService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_6__execption_exception__["a" /* Exception */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__execption_exception__["a" /* Exception */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_7__services_navbar_service__["a" /* NavbarService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_7__services_navbar_service__["a" /* NavbarService */]) === "function" && _d || Object])
], ListClassComponent);

var _a, _b, _c, _d;
//# sourceMappingURL=list-class.component.js.map

/***/ }),
/* 264 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_exams_service__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__models_exam__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_user__ = __webpack_require__(14);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__models_class__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__models_group__ = __webpack_require__(24);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__angular_router__ = __webpack_require__(4);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ListExameComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








var ListExameComponent = (function () {
    function ListExameComponent(authentication, examsService, router) {
        this.authentication = authentication;
        this.examsService = examsService;
        this.router = router;
        this.cleanAllArray();
    }
    ListExameComponent.prototype.ngOnInit = function () {
        this.getHistoryAndUpComming();
    };
    ListExameComponent.prototype.ngAfterViewInit = function () {
        panels();
    };
    ListExameComponent.prototype.cleanAllArray = function () {
        this.upComingExams = new Array();
        this.historyExams = new Array();
        this.onGoingExams = new Array();
    };
    ListExameComponent.prototype.getHistoryAndUpComming = function () {
        var _this = this;
        this.cleanAllArray();
        this.examsService.getExamsByUserId(this.authentication.getUserId()).subscribe(function (resultado) {
            if (resultado.exams.History) {
                _this.getAllHistory(resultado.exams.History);
            }
            if (resultado.exams.Upcoming) {
                _this.getAllComing(resultado.exams.Upcoming);
            }
        }, function (error) {
            console.log(error);
        });
    };
    ListExameComponent.prototype.getAllHistory = function (exams) {
        for (var _i = 0, exams_1 = exams; _i < exams_1.length; _i++) {
            var exam = exams_1[_i];
            this.historyExams.push(this.createExam(exam));
        }
    };
    ListExameComponent.prototype.getAllOngoing = function (exams) {
        for (var _i = 0, exams_2 = exams; _i < exams_2.length; _i++) {
            var exam = exams_2[_i];
            this.onGoingExams.push(this.createExam(exam));
        }
    };
    ListExameComponent.prototype.getAllComing = function (exams) {
        for (var _i = 0, exams_3 = exams; _i < exams_3.length; _i++) {
            var exam = exams_3[_i];
            this.upComingExams.push(this.createExam(exam));
        }
    };
    ListExameComponent.prototype.createExam = function (exam) {
        var teacher = exam.group._class.teacher;
        var _teacher = new __WEBPACK_IMPORTED_MODULE_4__models_user__["a" /* User */](teacher.id, teacher.email, teacher.firstName, teacher.lastName, teacher.type, '');
        var classe = exam.group._class;
        var _classe = new __WEBPACK_IMPORTED_MODULE_5__models_class__["a" /* Class */](classe.name, classe.abbreviation);
        _classe.id = classe.id;
        _classe.user = _teacher;
        var group = exam.group;
        var _group = new __WEBPACK_IMPORTED_MODULE_6__models_group__["a" /* Group */](group.name);
        _group.id = group.id;
        _group.class = _classe;
        var examnew = new __WEBPACK_IMPORTED_MODULE_3__models_exam__["a" /* Exam */](exam.name, exam.beginDate, exam.duration);
        examnew.id = exam.id;
        examnew.group = _group;
        return examnew;
    };
    ListExameComponent.prototype.refreshUpComing = function () {
        this.getHistoryAndUpComming();
    };
    ListExameComponent.prototype.refreshHistory = function () {
        this.getHistoryAndUpComming();
    };
    ListExameComponent.prototype.isTeacher = function () {
        return this.authentication.isTeacher();
    };
    ListExameComponent.prototype.goToExamResult = function (exam) {
        var _this = this;
        if (this.isTeacher()) {
            this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id, 'results']);
        }
        else {
            this.examsService.getSubmissionsByExam(exam.id, this.authentication.getUserId()).subscribe(function (result) {
                if (result && result[0]) {
                    _this.router.navigate(['/dashboard', 'classes', exam.group.class.id, 'groups', exam.group.id, 'exams', exam.id, 'submission', result[0].id]);
                }
            }, function (error) {
                console.log(error);
            });
        }
    };
    return ListExameComponent;
}());
ListExameComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-list-exame',
        template: __webpack_require__(379),
        styles: [__webpack_require__(348)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_authentication_service__["a" /* AuthenticationService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_exams_service__["a" /* ExamsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_exams_service__["a" /* ExamsService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_7__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_7__angular_router__["a" /* Router */]) === "function" && _c || Object])
], ListExameComponent);

var _a, _b, _c;
//# sourceMappingURL=list-exame.component.js.map

/***/ }),
/* 265 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StudentsFilter; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
/**
 * Created by pedro on 29-06-2017.
 */

var StudentsFilter = (function () {
    function StudentsFilter() {
    }
    StudentsFilter.prototype.transform = function (value, input) {
        if (input) {
            input = input.toLowerCase();
            return value.filter(function (el) {
                var pos = el.indexOf('@');
                var aux = el.substring(0, pos);
                return aux.toLowerCase().indexOf(input) > -1;
            });
        }
        return value;
    };
    return StudentsFilter;
}());
StudentsFilter = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Pipe"])({
        name: 'StudentsFilter',
    })
], StudentsFilter);

//# sourceMappingURL=students_filter.js.map

/***/ }),
/* 266 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StudentsFilterGroup; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
/**
 * Created by pedro on 29-06-2017.
 */

var StudentsFilterGroup = (function () {
    function StudentsFilterGroup() {
    }
    StudentsFilterGroup.prototype.transform = function (value, input) {
        if (input) {
            input = input.toLowerCase();
            return value.filter(function (el) {
                var pos = el.email.indexOf('@');
                var aux = el.email.substring(0, pos);
                return aux.toLowerCase().indexOf(input) > -1;
            });
        }
        return value;
    };
    return StudentsFilterGroup;
}());
StudentsFilterGroup = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Pipe"])({
        name: 'StudentsFilterGroup',
    })
], StudentsFilterGroup);

//# sourceMappingURL=students_filter_group.js.map

/***/ }),
/* 267 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StudentsFilterGroupScore; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
/**
 * Created by pedro on 02-07-2017.
 */

var StudentsFilterGroupScore = (function () {
    function StudentsFilterGroupScore() {
    }
    StudentsFilterGroupScore.prototype.transform = function (value, input) {
        if (input) {
            input = input.toLowerCase();
            return value.filter(function (el) {
                var pos = el.email.indexOf('@');
                var aux = el.email.substring(0, pos);
                return aux.toLowerCase().indexOf(input) > -1;
            });
        }
        return value;
    };
    return StudentsFilterGroupScore;
}());
StudentsFilterGroupScore = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Pipe"])({
        name: 'StudentsFilterGroupScore',
    })
], StudentsFilterGroupScore);

//# sourceMappingURL=students_filter_group_score.js.map

/***/ }),
/* 268 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StudentsFilterResultAll; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
/**
 * Created by pedro on 02-07-2017.
 */

var StudentsFilterResultAll = (function () {
    function StudentsFilterResultAll() {
    }
    StudentsFilterResultAll.prototype.transform = function (value, input) {
        if (input) {
            input = input.toLowerCase();
            return value.filter(function (el) {
                var pos = el.user.email.indexOf('@');
                var aux = el.user.email.substring(0, pos);
                return aux.toLowerCase().indexOf(input) > -1;
            });
        }
        return value;
    };
    return StudentsFilterResultAll;
}());
StudentsFilterResultAll = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Pipe"])({
        name: 'StudentsFilterResultAll',
    })
], StudentsFilterResultAll);

//# sourceMappingURL=students_filter_result_all.js.map

/***/ }),
/* 269 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__http_util_service__ = __webpack_require__(11);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__authentication_service__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return InvitationsService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var InvitationsService = (function () {
    function InvitationsService(router, http, httpUtil, authentication) {
        this.router = router;
        this.http = http;
        this.httpUtil = httpUtil;
        this.authentication = authentication;
    }
    // GET /api/invitations/{invitation_id}/accept
    InvitationsService.prototype.getInvitationAccept = function (invitationId) {
        return this.http.get(this.httpUtil.url('/api/invitations/' + invitationId + '/accept'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    // GET /api/invitations/{invitation_id}/decline
    InvitationsService.prototype.getInvitationDecline = function (invitationId) {
        return this.http.get(this.httpUtil.url('/api/invitations/' + invitationId + '/decline'), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
    };
    return InvitationsService;
}());
InvitationsService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__http_util_service__["a" /* HttpUtilService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__authentication_service__["a" /* AuthenticationService */]) === "function" && _d || Object])
], InvitationsService);

var _a, _b, _c, _d;
//# sourceMappingURL=invitations.service.js.map

/***/ }),
/* 270 */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
// The file contents for the current environment will overwrite these during build.
var environment = {
    production: false
};
//# sourceMappingURL=environment.js.map

/***/ }),
/* 271 */,
/* 272 */,
/* 273 */,
/* 274 */,
/* 275 */,
/* 276 */,
/* 277 */,
/* 278 */,
/* 279 */,
/* 280 */,
/* 281 */,
/* 282 */,
/* 283 */,
/* 284 */,
/* 285 */,
/* 286 */,
/* 287 */,
/* 288 */,
/* 289 */,
/* 290 */,
/* 291 */,
/* 292 */,
/* 293 */,
/* 294 */,
/* 295 */,
/* 296 */,
/* 297 */,
/* 298 */,
/* 299 */,
/* 300 */,
/* 301 */,
/* 302 */,
/* 303 */,
/* 304 */,
/* 305 */,
/* 306 */,
/* 307 */,
/* 308 */,
/* 309 */,
/* 310 */,
/* 311 */,
/* 312 */,
/* 313 */,
/* 314 */,
/* 315 */,
/* 316 */,
/* 317 */,
/* 318 */,
/* 319 */,
/* 320 */,
/* 321 */,
/* 322 */,
/* 323 */,
/* 324 */,
/* 325 */,
/* 326 */,
/* 327 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".bd {\n  padding-top: 100px;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 328 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 329 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 330 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".question-add {\n  padding: 15px 5px 15px 5px;\n  border-bottom: solid;\n  border-bottom-width: 1px;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 331 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 332 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 333 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 334 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 335 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".question-submit {\n  padding: 15px 5px 15px 5px;\n  border-bottom: solid;\n  border-bottom-width: 1px;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 336 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 337 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".list-students {\n  height: 400px;\n}\n.groupAlreadyExists{\n  color: red;\n  padding-top: 0px;\n}\n\n.groupCreated{\n  color: green;\n  padding-top: 0px;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 338 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 339 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".list {\n  max-height: 500px;\n}\n.filter-group-exames{\n  margin-bottom: 8px;\n  padding-top: 5px;\n}\n\n.filter-group-students{\n  padding-top: 5px;\n}\n\n.noselect {\n  -webkit-touch-callout: none; /* iOS Safari */\n  -webkit-user-select: none; /* Safari */ /* Konqueror HTML */\n  -moz-user-select: none; /* Firefox */\n  -ms-user-select: none; /* Internet Explorer/Edge */\n  user-select: none; /* Non-prefixed version, currently\n                                  supported by Chrome and Opera */\n}\n\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 340 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 341 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 342 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".border-group {\n  padding-bottom: 10px;\n  border-bottom: solid;\n  border-bottom-width: 1px;\n}\n\n.needRadio {\n  color: red;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 343 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 344 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".border-group {\n  padding-bottom: 10px;\n  border-bottom: solid;\n  border-bottom-width: 1px;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 345 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 346 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".classAlreadyExists{\n  color: red;\n  padding-top: 0px;\n}\n\n.classCreated{\n  color: green;\n  padding-top: 0px;\n}\n\n.abbreviation{\n  margin-right: 1px;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 347 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 348 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".list-exames {\n  max-height: 250px;\n}\n\n.ongoing{\n  color: red;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 349 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 350 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 351 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "/*!\n * Start Bootstrap - Freelancer v3.3.7+1 (http://startbootstrap.com/template-overviews/freelancer)\n * Copyright 2013-2016 Start Bootstrap\n * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap/blob/gh-pages/LICENSE)\n */\nbody {\n  font-family: 'Open Sans', sans-serif;\n  overflow-x: hidden;\n}\np {\n  font-size: 20px;\n}\np.small {\n  font-size: 16px;\n}\na,\na:hover,\na:focus,\na:active,\na.active {\n  color: #1caf9a;\n}\nh1,\nh2,\nh3,\nh4,\nh5,\nh6 {\n  text-transform: uppercase;\n  font-weight: 700;\n}\nhr.star-light,\nhr.star-primary {\n  padding: 0;\n  border: none;\n  border-top: solid 5px;\n  text-align: center;\n  max-width: 250px;\n  margin: 25px auto 30px;\n}\nhr.star-light:after,\nhr.star-primary:after {\n  content: \"\\F005\";\n  font-family: FontAwesome;\n  display: inline-block;\n  position: relative;\n  top: -0.8em;\n  font-size: 2em;\n  padding: 0 0.25em;\n}\nhr.star-light {\n  border-color: white;\n}\nhr.star-light:after {\n  background-color: #1caf9a;\n  color: white;\n}\nhr.star-primary {\n  border-color: #22262e;\n}\nhr.star-primary:after {\n  background-color: white;\n  color: #22262e;\n}\n.img-centered {\n  margin: 0 auto;\n}\nheader {\n  text-align: center;\n  background: #1caf9a;\n  color: white;\n}\nheader .container {\n  padding-top: 100px;\n  padding-bottom: 50px;\n}\nheader img {\n  display: block;\n  margin: 0 auto 20px;\n}\nheader .intro-text .name {\n  display: block;\n  font-family: \"Montserrat\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n  text-transform: uppercase;\n  font-weight: 700;\n  font-size: 2em;\n}\nheader .intro-text .skills {\n  font-size: 1.25em;\n  font-weight: 300;\n}\n@media (min-width: 768px) {\n  header .container {\n    padding-top: 200px;\n    padding-bottom: 100px;\n  }\n  header .intro-text .name {\n    font-size: 4.75em;\n  }\n  header .intro-text .skills {\n    font-size: 1.75em;\n  }\n}\n.navbar-custom {\n  background: #22262e;\n  font-family: \"Montserrat\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n  text-transform: uppercase;\n  font-weight: 700;\n  border: none;\n}\n.navbar-custom .navbar-brand {\n  color: white;\n}\n.navbar-custom .navbar-brand:hover,\n.navbar-custom .navbar-brand:focus,\n.navbar-custom .navbar-brand:active,\n.navbar-custom .navbar-brand.active {\n  color: white;\n}\n.navbar-custom .navbar-nav {\n  letter-spacing: 1px;\n}\n.navbar-custom .navbar-nav li a {\n  color: white;\n}\n.navbar-custom .navbar-nav li a:hover {\n  color: #1caf9a;\n}\n.navbar-custom .navbar-nav li a:focus,\n.navbar-custom .navbar-nav li a:active {\n  color: white;\n}\n.navbar-custom .navbar-nav li.active a {\n  color: white;\n  background: #1caf9a;\n}\n.navbar-custom .navbar-nav li.active a:hover,\n.navbar-custom .navbar-nav li.active a:focus,\n.navbar-custom .navbar-nav li.active a:active {\n  color: white;\n  background: #1caf9a;\n}\n.navbar-custom .navbar-toggle {\n  color: white;\n  text-transform: uppercase;\n  font-size: 10px;\n  border-color: white;\n}\n.navbar-custom .navbar-toggle:hover,\n.navbar-custom .navbar-toggle:focus {\n  background-color: #1caf9a;\n  color: white;\n  border-color: #1caf9a;\n}\n@media (min-width: 800px) {\n  .navbar-custom {\n    padding: 25px 0;\n    transition: padding 0.3s;\n  }\n  .navbar-custom .navbar-brand {\n    font-size: 2em;\n    transition: all 0.3s;\n  }\n  .navbar-custom.affix {\n    padding: 10px 0;\n  }\n  .navbar-custom.affix .navbar-brand {\n    font-size: 1.5em;\n  }\n}\nsection {\n  padding: 100px 0;\n}\nsection h2 {\n  margin: 0;\n  font-size: 3em;\n}\nsection.success {\n  background: #1caf9a;\n  color: white;\n}\n@media (max-width: 767px) {\n  section {\n    padding: 75px 0;\n  }\n  section.first {\n    padding-top: 75px;\n  }\n}\n#portfolio .portfolio-item {\n  margin: 0 0 15px;\n  right: 0;\n}\n#portfolio .portfolio-item .portfolio-link {\n  display: block;\n  position: relative;\n  max-width: 400px;\n  margin: 0 auto;\n}\n#portfolio .portfolio-item .portfolio-link .caption {\n  background: rgba(24, 188, 156, 0.9);\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  opacity: 0;\n  transition: all ease 0.5s;\n  -webkit-transition: all ease 0.5s;\n  -moz-transition: all ease 0.5s;\n}\n#portfolio .portfolio-item .portfolio-link .caption:hover {\n  opacity: 1;\n}\n#portfolio .portfolio-item .portfolio-link .caption .caption-content {\n  position: absolute;\n  width: 100%;\n  height: 20px;\n  font-size: 20px;\n  text-align: center;\n  top: 50%;\n  margin-top: -12px;\n  color: white;\n}\n#portfolio .portfolio-item .portfolio-link .caption .caption-content i {\n  margin-top: -12px;\n}\n#portfolio .portfolio-item .portfolio-link .caption .caption-content h3,\n#portfolio .portfolio-item .portfolio-link .caption .caption-content h4 {\n  margin: 0;\n}\n#portfolio * {\n  z-index: 2;\n}\n@media (min-width: 767px) {\n  #portfolio .portfolio-item {\n    margin: 0 0 30px;\n  }\n}\n.floating-label-form-group {\n  position: relative;\n  margin-bottom: 0;\n  padding-bottom: 0.5em;\n  border-bottom: 1px solid #eeeeee;\n}\n.floating-label-form-group input,\n.floating-label-form-group textarea {\n  z-index: 1;\n  position: relative;\n  padding-right: 0;\n  padding-left: 0;\n  border: none;\n  border-radius: 0;\n  font-size: 1.5em;\n  background: none;\n  box-shadow: none !important;\n  resize: none;\n}\n.floating-label-form-group label {\n  display: block;\n  z-index: 0;\n  position: relative;\n  top: 2em;\n  margin: 0;\n  font-size: 0.85em;\n  line-height: 1.764705882em;\n  vertical-align: middle;\n  vertical-align: baseline;\n  opacity: 0;\n  transition: top 0.3s ease,opacity 0.3s ease;\n}\n.floating-label-form-group:not(:first-child) {\n  padding-left: 14px;\n  border-left: 1px solid #eeeeee;\n}\n.floating-label-form-group-with-value label {\n  top: 0;\n  opacity: 1;\n}\n.floating-label-form-group-with-focus label {\n  color: #1caf9a;\n}\nform .row:first-child .floating-label-form-group {\n  border-top: 1px solid #eeeeee;\n}\nfooter {\n  color: white;\n}\nfooter h3 {\n  margin-bottom: 30px;\n}\nfooter .footer-above {\n  padding-top: 50px;\n  background-color: #22262e;\n}\nfooter .footer-col {\n  margin-bottom: 50px;\n}\nfooter .footer-below {\n  padding: 25px 0;\n  background-color: #233140;\n}\n.btn-outline {\n  color: white;\n  font-size: 20px;\n  border: solid 2px white;\n  background: transparent;\n  transition: all 0.3s ease-in-out;\n  margin-top: 15px;\n}\n.btn-outline:hover,\n.btn-outline:focus,\n.btn-outline:active,\n.btn-outline.active {\n  color: #1caf9a;\n  background: white;\n  border: solid 2px white;\n}\n.btn-primary {\n  color: white;\n  background-color: #22262e;\n  border-color: #22262e;\n  font-weight: 700;\n}\n.btn-primary:hover,\n.btn-primary:focus,\n.btn-primary:active,\n.btn-primary.active,\n.open .dropdown-toggle.btn-primary {\n  color: white;\n  background-color: #1a242f;\n  border-color: #161f29;\n}\n.btn-primary:active,\n.btn-primary.active,\n.open .dropdown-toggle.btn-primary {\n  background-image: none;\n}\n.btn-primary.disabled,\n.btn-primary[disabled],\nfieldset[disabled] .btn-primary,\n.btn-primary.disabled:hover,\n.btn-primary[disabled]:hover,\nfieldset[disabled] .btn-primary:hover,\n.btn-primary.disabled:focus,\n.btn-primary[disabled]:focus,\nfieldset[disabled] .btn-primary:focus,\n.btn-primary.disabled:active,\n.btn-primary[disabled]:active,\nfieldset[disabled] .btn-primary:active,\n.btn-primary.disabled.active,\n.btn-primary[disabled].active,\nfieldset[disabled] .btn-primary.active {\n  background-color: #22262e;\n  border-color: #22262e;\n}\n.btn-primary .badge {\n  color: #22262e;\n  background-color: white;\n}\n.btn-success {\n  color: white;\n  background-color: #1caf9a;\n  border-color: #1caf9a;\n  font-weight: 700;\n}\n.btn-success:hover,\n.btn-success:focus,\n.btn-success:active,\n.btn-success.active,\n.open .dropdown-toggle.btn-success {\n  color: white;\n  background-color: #128f76;\n  border-color: #11866f;\n}\n.btn-success:active,\n.btn-success.active,\n.open .dropdown-toggle.btn-success {\n  background-image: none;\n}\n.btn-success.disabled,\n.btn-success[disabled],\nfieldset[disabled] .btn-success,\n.btn-success.disabled:hover,\n.btn-success[disabled]:hover,\nfieldset[disabled] .btn-success:hover,\n.btn-success.disabled:focus,\n.btn-success[disabled]:focus,\nfieldset[disabled] .btn-success:focus,\n.btn-success.disabled:active,\n.btn-success[disabled]:active,\nfieldset[disabled] .btn-success:active,\n.btn-success.disabled.active,\n.btn-success[disabled].active,\nfieldset[disabled] .btn-success.active {\n  background-color: #1caf9a;\n  border-color: #1caf9a;\n}\n.btn-success .badge {\n  color: #1caf9a;\n  background-color: white;\n}\n.btn-social {\n  display: inline-block;\n  height: 50px;\n  width: 50px;\n  border: 2px solid white;\n  border-radius: 100%;\n  text-align: center;\n  font-size: 20px;\n  line-height: 45px;\n}\n.scroll-top {\n  position: fixed;\n  right: 2%;\n  bottom: 2%;\n  width: 50px;\n  height: 50px;\n  z-index: 1049;\n}\n.scroll-top .btn {\n  font-size: 20px;\n  width: 50px;\n  height: 50px;\n  border-radius: 100%;\n  line-height: 28px;\n}\n.portfolio-modal .modal-content {\n  border-radius: 0;\n  background-clip: border-box;\n  box-shadow: none;\n  border: none;\n  min-height: 100%;\n  padding: 100px 0;\n  text-align: center;\n}\n.portfolio-modal .modal-content h2 {\n  margin: 0;\n  font-size: 3em;\n}\n.portfolio-modal .modal-content img {\n  margin-bottom: 30px;\n}\n.portfolio-modal .modal-content .item-details {\n  margin: 30px 0;\n}\n.portfolio-modal .close-modal {\n  position: absolute;\n  width: 75px;\n  height: 75px;\n  background-color: transparent;\n  top: 25px;\n  right: 25px;\n  cursor: pointer;\n}\n.portfolio-modal .close-modal:hover {\n  opacity: 0.3;\n}\n.portfolio-modal .close-modal .lr {\n  height: 75px;\n  width: 1px;\n  margin-left: 35px;\n  background-color: #22262e;\n  transform: rotate(45deg);\n  -ms-transform: rotate(45deg);\n  /* IE 9 */\n  -webkit-transform: rotate(45deg);\n  /* Safari and Chrome */\n  z-index: 1051;\n}\n.portfolio-modal .close-modal .lr .rl {\n  height: 75px;\n  width: 1px;\n  background-color: #22262e;\n  transform: rotate(90deg);\n  -ms-transform: rotate(90deg);\n  /* IE 9 */\n  -webkit-transform: rotate(90deg);\n  /* Safari and Chrome */\n  z-index: 1052;\n}\n.portfolio-modal .modal-backdrop {\n  opacity: 0;\n  display: none;\n}\n#skipnav a {\n  padding: 6px;\n  position: absolute;\n  top: -40px;\n  left: 0px;\n  color: white;\n  border-right: 1px solid white;\n  border-bottom: 1px solid white;\n  border-bottom-right-radius: 8px;\n  background: transparent;\n  transition: top 1s ease-out, background 1s linear;\n  z-index: 2000;\n}\n#skipnav a:focus {\n  position: absolute;\n  left: 0px;\n  top: 0px;\n  background: #1caf9a;\n  outline: 0;\n  transition: top 0.1s ease-in, background 0.5s linear;\n}\ndiv#maincontent {\n  outline: none;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 352 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 353 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 354 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".profile-padding {\n  padding-bottom: 10px;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 355 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(2)();
// imports


// module
exports.push([module.i, ".typeUser{\n  color: whitesmoke;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),
/* 356 */,
/* 357 */
/***/ (function(module, exports, __webpack_require__) {

var map = {
	"./af": 119,
	"./af.js": 119,
	"./ar": 126,
	"./ar-dz": 120,
	"./ar-dz.js": 120,
	"./ar-kw": 121,
	"./ar-kw.js": 121,
	"./ar-ly": 122,
	"./ar-ly.js": 122,
	"./ar-ma": 123,
	"./ar-ma.js": 123,
	"./ar-sa": 124,
	"./ar-sa.js": 124,
	"./ar-tn": 125,
	"./ar-tn.js": 125,
	"./ar.js": 126,
	"./az": 127,
	"./az.js": 127,
	"./be": 128,
	"./be.js": 128,
	"./bg": 129,
	"./bg.js": 129,
	"./bn": 130,
	"./bn.js": 130,
	"./bo": 131,
	"./bo.js": 131,
	"./br": 132,
	"./br.js": 132,
	"./bs": 133,
	"./bs.js": 133,
	"./ca": 134,
	"./ca.js": 134,
	"./cs": 135,
	"./cs.js": 135,
	"./cv": 136,
	"./cv.js": 136,
	"./cy": 137,
	"./cy.js": 137,
	"./da": 138,
	"./da.js": 138,
	"./de": 141,
	"./de-at": 139,
	"./de-at.js": 139,
	"./de-ch": 140,
	"./de-ch.js": 140,
	"./de.js": 141,
	"./dv": 142,
	"./dv.js": 142,
	"./el": 143,
	"./el.js": 143,
	"./en-au": 144,
	"./en-au.js": 144,
	"./en-ca": 145,
	"./en-ca.js": 145,
	"./en-gb": 146,
	"./en-gb.js": 146,
	"./en-ie": 147,
	"./en-ie.js": 147,
	"./en-nz": 148,
	"./en-nz.js": 148,
	"./eo": 149,
	"./eo.js": 149,
	"./es": 151,
	"./es-do": 150,
	"./es-do.js": 150,
	"./es.js": 151,
	"./et": 152,
	"./et.js": 152,
	"./eu": 153,
	"./eu.js": 153,
	"./fa": 154,
	"./fa.js": 154,
	"./fi": 155,
	"./fi.js": 155,
	"./fo": 156,
	"./fo.js": 156,
	"./fr": 159,
	"./fr-ca": 157,
	"./fr-ca.js": 157,
	"./fr-ch": 158,
	"./fr-ch.js": 158,
	"./fr.js": 159,
	"./fy": 160,
	"./fy.js": 160,
	"./gd": 161,
	"./gd.js": 161,
	"./gl": 162,
	"./gl.js": 162,
	"./gom-latn": 163,
	"./gom-latn.js": 163,
	"./he": 164,
	"./he.js": 164,
	"./hi": 165,
	"./hi.js": 165,
	"./hr": 166,
	"./hr.js": 166,
	"./hu": 167,
	"./hu.js": 167,
	"./hy-am": 168,
	"./hy-am.js": 168,
	"./id": 169,
	"./id.js": 169,
	"./is": 170,
	"./is.js": 170,
	"./it": 171,
	"./it.js": 171,
	"./ja": 172,
	"./ja.js": 172,
	"./jv": 173,
	"./jv.js": 173,
	"./ka": 174,
	"./ka.js": 174,
	"./kk": 175,
	"./kk.js": 175,
	"./km": 176,
	"./km.js": 176,
	"./kn": 177,
	"./kn.js": 177,
	"./ko": 178,
	"./ko.js": 178,
	"./ky": 179,
	"./ky.js": 179,
	"./lb": 180,
	"./lb.js": 180,
	"./lo": 181,
	"./lo.js": 181,
	"./lt": 182,
	"./lt.js": 182,
	"./lv": 183,
	"./lv.js": 183,
	"./me": 184,
	"./me.js": 184,
	"./mi": 185,
	"./mi.js": 185,
	"./mk": 186,
	"./mk.js": 186,
	"./ml": 187,
	"./ml.js": 187,
	"./mr": 188,
	"./mr.js": 188,
	"./ms": 190,
	"./ms-my": 189,
	"./ms-my.js": 189,
	"./ms.js": 190,
	"./my": 191,
	"./my.js": 191,
	"./nb": 192,
	"./nb.js": 192,
	"./ne": 193,
	"./ne.js": 193,
	"./nl": 195,
	"./nl-be": 194,
	"./nl-be.js": 194,
	"./nl.js": 195,
	"./nn": 196,
	"./nn.js": 196,
	"./pa-in": 197,
	"./pa-in.js": 197,
	"./pl": 198,
	"./pl.js": 198,
	"./pt": 200,
	"./pt-br": 199,
	"./pt-br.js": 199,
	"./pt.js": 200,
	"./ro": 201,
	"./ro.js": 201,
	"./ru": 202,
	"./ru.js": 202,
	"./sd": 203,
	"./sd.js": 203,
	"./se": 204,
	"./se.js": 204,
	"./si": 205,
	"./si.js": 205,
	"./sk": 206,
	"./sk.js": 206,
	"./sl": 207,
	"./sl.js": 207,
	"./sq": 208,
	"./sq.js": 208,
	"./sr": 210,
	"./sr-cyrl": 209,
	"./sr-cyrl.js": 209,
	"./sr.js": 210,
	"./ss": 211,
	"./ss.js": 211,
	"./sv": 212,
	"./sv.js": 212,
	"./sw": 213,
	"./sw.js": 213,
	"./ta": 214,
	"./ta.js": 214,
	"./te": 215,
	"./te.js": 215,
	"./tet": 216,
	"./tet.js": 216,
	"./th": 217,
	"./th.js": 217,
	"./tl-ph": 218,
	"./tl-ph.js": 218,
	"./tlh": 219,
	"./tlh.js": 219,
	"./tr": 220,
	"./tr.js": 220,
	"./tzl": 221,
	"./tzl.js": 221,
	"./tzm": 223,
	"./tzm-latn": 222,
	"./tzm-latn.js": 222,
	"./tzm.js": 223,
	"./uk": 224,
	"./uk.js": 224,
	"./ur": 225,
	"./ur.js": 225,
	"./uz": 227,
	"./uz-latn": 226,
	"./uz-latn.js": 226,
	"./uz.js": 227,
	"./vi": 228,
	"./vi.js": 228,
	"./x-pseudo": 229,
	"./x-pseudo.js": 229,
	"./yo": 230,
	"./yo.js": 230,
	"./zh-cn": 231,
	"./zh-cn.js": 231,
	"./zh-hk": 232,
	"./zh-hk.js": 232,
	"./zh-tw": 233,
	"./zh-tw.js": 233
};
function webpackContext(req) {
	return __webpack_require__(webpackContextResolve(req));
};
function webpackContextResolve(req) {
	var id = map[req];
	if(!(id + 1)) // check for number
		throw new Error("Cannot find module '" + req + "'.");
	return id;
};
webpackContext.keys = function webpackContextKeys() {
	return Object.keys(map);
};
webpackContext.resolve = webpackContextResolve;
module.exports = webpackContext;
webpackContext.id = 357;


/***/ }),
/* 358 */
/***/ (function(module, exports) {

module.exports = "<router-outlet></router-outlet>\n"

/***/ }),
/* 359 */
/***/ (function(module, exports) {

module.exports = "<div class=\"row\">\n  <div class=\"col-lg-8 col-md-8 col-lg-offset-4 col-md-offset-4\" *ngIf=\"isTeacher()\">\n    <form class=\"form-horizontal\" role=\"form\">\n      <div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n        <button (click)=\"clickRemoveClass()\" class=\"btn btn-block btn-danger \">\n          <span class=\"fa fa-minus-square-o\"></span>Class\n        </button>\n      </div>\n      <div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n        <button [routerLink]=\"['/dashboard','classes',this.classId,'questions' ]\" class=\"btn btn-block btn-primary \">\n          <span class=\"fa fa-eye\"></span>Questions\n        </button>\n      </div>\n      <div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n        <button [routerLink]=\"['/dashboard','classes',this.classId,'questions','new' ]\" class=\"btn btn-block btn-primary \">\n          <span class=\"fa fa-plus-square\"></span>Questions\n        </button>\n      </div>\n      <div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n        <button [routerLink]=\"['/dashboard','classes',this.classId,'groups','new']\" class=\"btn btn-block btn-primary \">\n          <span class=\"fa fa-plus-square\"></span>Group\n        </button>\n      </div>\n    </form>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-lg-12 col-md-12\">\n    <div class=\"panel panel-default\">\n      <div class=\"panel-heading ui-draggable-handle\">\n        <h3 class=\"panel-title\">{{classInformation.abbreviation}} - {{classInformation.name}}</h3>\n        <ul class=\"panel-controls\">\n          <li><a><span class=\"fa fa-edit\"></span></a></li>\n          <li><a class=\"panel-collapse\"><span class=\"fa fa-angle-down\"></span></a></li>\n        </ul>\n      </div>\n      <div class=\"panel-body\">\n        <div>\n          <label>Teacher:</label>\n          <span>{{teacherClass._firstName}} {{teacherClass._lastName}} ({{teacherClass.email}})</span>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n\n<br>\n\n<!--<popup #popup2 (confirmClick)=\"confirmDeleteClass()\" (cancelClick)=\"cancelDeleteClass()\">-->\n  <!--Are you sure you want to delete the Class?-->\n<!--</popup>-->\n\n<app-groups></app-groups>\n\n"

/***/ }),
/* 360 */
/***/ (function(module, exports) {

module.exports = "<div class=\"row\">\n  <div class=\"col-md-12\">\n    <h2 class=\"title-line\">Exam Create</h2>\n  </div>\n</div>\n\n<br>\n\n<form class=\"form-horizontal\" name=\"form\"  (ngSubmit)=\"f_createExam.form.valid && createExam()\" #f_createExam=\"ngForm\" novalidate>\n\n<div class=\"row\">\n  <div class=\"col-md-4 col-md-offset-8 col-sm-6 col-sm-offset-6 col-xs-12\">\n    <div class=\"col-md-6\">\n      <button (click)=\"generateAllNow()\" type=\"button\" class=\"btn btn-primary btn-block\">Generate All</button>\n    </div>\n    <div class=\"col-md-6\">\n      <button type=\"submit\" class=\"btn btn-primary btn-block\">Create</button>\n    </div>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <form role=\"form\">\n  <div class=\"col-md-6 col-sm-6 col-xs-12\">\n      <div class=\"form-group\" [ngClass]=\"{ 'has-error': f_createExam.submitted && !nameExam.valid }\">\n        <label class=\"col-md-3 control-label\">Exam Name:</label>\n        <div class=\"col-md-9\">\n          <div class=\"input-group\">\n            <input type=\"text\" class=\"form-control\" autocomplete=\"off\" name=\"nameExam\" [(ngModel)]=\"examCreate.nameExam\" placeholder=\"Exam Name\" #nameExam=\"ngModel\" required/>\n            <span class=\"input-group-addon\"><span class=\"fa fa-pencil\"></span></span>\n          </div>\n          <div *ngIf=\"f_createExam.submitted && !nameExam.valid\" class=\"help-block\">Exam name is required</div>\n        </div>\n      </div>\n\n      <div class=\"form-group\" [ngClass]=\"{ 'has-error': (f_createExam.submitted && !dateExam.valid) || (f_createExam.submitted && invalidDate) }\">\n        <label class=\"col-md-3 control-label\">Date</label>\n        <div class=\"col-md-9\">\n          <div class=\"input-group\">\n            <input class=\"form-control\" type=\"date\" placeholder=\"dd-mm-yyyy\" name=\"dateExam\" [(ngModel)]=\"examCreate.dateExam\" #dateExam=\"ngModel\" required>\n            <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-calendar\"></span></span>\n          </div>\n          <div *ngIf=\"f_createExam.submitted && !dateExam.valid\" class=\"help-block\">Date is required</div>\n          <div *ngIf=\"f_createExam.submitted && invalidDate\" class=\"help-block\">Exam date must be higher than today</div>\n          <span class=\"help-block\"></span>\n        </div>\n      </div>\n\n      <div class=\"form-group\" [ngClass]=\"{ 'has-error': f_createExam.submitted && !hourExam.valid }\">\n        <label class=\"col-md-3 control-label\">Hour</label>\n        <div class=\"col-md-9\">\n          <div class=\"input-group\">\n            <input class=\"form-control\" type=\"time\" placeholder=\"hh-mm\" name=\"hourExam\" [(ngModel)]=\"examCreate.hourExam\" #hourExam=\"ngModel\" required>\n            <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-time\"></span></span>\n          </div>\n          <div *ngIf=\"f_createExam.submitted && !hourExam.valid\" class=\"help-block\">Exam Hour is required</div>\n        </div>\n        <span class=\"help-block\"></span>\n      </div>\n\n      <div class=\"form-group\" [ngClass]=\"{ 'has-error': f_createExam.submitted && !duration.valid}\">\n        <label class=\"col-md-3 control-label\">Duration</label>\n        <div class=\"col-md-7\">\n\n          <div class=\"input-group\">\n            <input class=\"form-control\" type=\"number\" placeholder=\"6\" min=\"1\" step=\"1\" value =\"1\" name=\"duration\" [(ngModel)]=\"examCreate.duration\" #duration=\"ngModel\" required>\n            <!-- Colocar no placeholder o numero de questions acitvas -->\n          </div>\n          <div *ngIf=\"f_createExam.submitted && !duration.valid\" class=\"help-block\">Number of questions is required</div>\n          <div *ngIf=\"examCreate.duration < 1 && duration.valid\" class=\"help-block\">Duration Invalid</div>\n        </div>\n        <span class=\"help-block\"></span>\n      </div>\n\n      <div class=\"form-group\" [ngClass]=\"{ 'has-error': f_createExam.submitted && !questionNumber.valid }\">\n        <label class=\"col-md-3 control-label\">Number of Questions:</label>\n        <div class=\"col-md-7\">\n\n          <div class=\"input-group\">\n            <input class=\"form-control\" type=\"number\" (focusout)=\"focusOutQuestionNumber()\" placeholder=\"6\" type=\"number\" min=\"1\" step=\"1\" value =\"1\" name=\"questionNumber\" [(ngModel)]=\"examCreate.questionNumber\" #questionNumber=\"ngModel\" required>\n            <!-- Colocar no placeholder o numero de questions acitvas -->\n          </div>\n          <div *ngIf=\"f_createExam.submitted && !questionNumber.valid\" class=\"help-block\">Number of questions is required</div>\n        </div>\n        <span class=\"help-block\"></span>\n      </div>\n  </div>\n\n  </form>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-12\">\n    <div class=\"panel panel-default\">\n      <div class=\"panel-heading ui-draggable-handle\">\n        <h3 class=\"panel-title\">Questions </h3>\n\n\n        <ul class=\"panel-controls\">\n          <li><a (click)=\"addQuestion()\"><span class=\"fa fa-plus\"></span></a></li>\n          <!--<li><a class=\"panel-collapse\"><span class=\"fa fa-angle-down\"></span></a></li>-->\n        </ul>\n      </div>\n      <div class=\"panel-body\">\n        <div class=\"panel-body\" >\n            <app-question-add *ngFor=\"let question of questions\" [groupId]=\"groupId\" [question]=\"question\" [updateDificulty]=\"updateDificulty\" [saveAll]=\"saveAll\" [generateAll]=\"generateAll\" [categories]=\"categoriesName\" [allQuestionsAvailable]=\"allQuestionsAvailable\" [allGenerateNow]=\"allGenerateNow\" (changeNow)=\"changeNow()\" (removeQuestion)=\"removeQuest($event)\" (removeFromAllQuestionsAvailable)=\"removeFromAllQuestionsAvailable($event)\" ></app-question-add>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n\n<div class=\"row\">\n  <div class=\"col-md-4 col-md-offset-8 col-sm-6 col-sm-offset-6 col-xs-12\">\n    <div class=\"col-md-6\">\n      <button (click)=\"generateAllNow()\" type=\"button\" class=\"btn btn-primary btn-block\">Generate All</button>\n    </div>\n    <div class=\"col-md-6\">\n      <button (click)=\"addQuestion()\" type=\"button\" class=\"btn btn-primary btn-block\">Add Question</button>\n    </div>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-4 col-md-offset-8 col-sm-6 col-sm-offset-6 col-xs-12\">\n    <div class=\"col-md-6 col-md-offset-6\">\n      <button type=\"submit\" class=\"btn btn-primary btn-block\">Create</button>\n    </div>\n  </div>\n</div>\n\n</form>\n\n<br>\n<br>\n\n\n"

/***/ }),
/* 361 */
/***/ (function(module, exports) {

module.exports = "<div class=\"question-add\">\n  <div class=\"row\">\n    <form>\n      <div class=\"form-group\">\n\n        <div class=\"col-md-5\">\n          <div class=\"col-md-4\">\n            <label class=\"col-md-3 control-label\">Category:</label>\n          </div>\n          <div class=\"col-md-8\">\n            <select (ngModelChange)=\"selectDificultyAvailable()\" class=\"form-control select\" name=\"category\" [(ngModel)]=\"questionModel.category\" required>\n              <option *ngFor=\"let categorie of categories\" [value]=\"categorie\">{{categorie}}</option>\n            </select>\n          </div>\n        </div>\n\n        <div class=\"col-md-5\">\n          <div class=\"col-md-4\">\n            <label class=\"col-md-3 control-label\">Dificulty:</label>\n          </div>\n          <div class=\"col-md-8\">\n            <select (click)=\"removeFromallQuestionsAvailables()\" class=\"form-control select\" name=\"dificult\" [(ngModel)]=\"questionModel.dificult\" required>\n              <option *ngFor=\"let dificult of dificultys\" [value]=\"dificult.value\" >{{dificult.dificulty}}</option>\n            </select>\n          </div>\n        </div>\n\n        <div class=\"col-md-2\">\n          <div class=\"col-md-8\">\n            <button (click)=\"generateQuestion()\" type=\"button\" class=\"btn btn-primary btn-block\">Generate</button>\n          </div>\n          <div class=\"col-md-2\" style=\"padding-top: 10px\">\n            <span (click)=\"removeQuestionNow()\" class=\"glyphicon glyphicon-remove pull-right text-danger\"></span>\n          </div>\n          <div class=\"col-md-2\" style=\"padding: 2px 10px 0px 0px\">\n            <button (click)=\"toggleDiplay()\" ng-disabled=\"this.display_details\" type=\"button\" class=\"btn btn-primary btn-rounded\"><span class=\"fa fa-angle-down\"></span></button>\n          </div>\n        </div>\n      </div>\n    </form>\n  </div>\n\n  <div class=\"row\">\n    <app-question-preview *ngIf=\"this.toogle_display_details\" [question]=\"question\"></app-question-preview>\n  </div>\n\n</div>\n"

/***/ }),
/* 362 */
/***/ (function(module, exports) {

module.exports = "<br>\n\n<div class=\"row\">\n  <div class=\"col-md-4 pull-right\">\n    <div class=\"form-group col-md-12 col-sm-12 col-xs-12\">\n      <div class=\"col-md-3 col-sm-3 col-xs-5\">\n        <label class=\"control-label\">Category:</label>\n      </div>\n      <div class=\"input-group col-md-9 col-sm-9 col-xs-7\">\n        <span class=\"text\">{{question.category}}</span>\n      </div>\n      <span class=\"help-block\"></span>\n    </div>\n\n\n    <div class=\"form-group col-md-12 col-sm-12 col-xs-12\">\n      <div class=\"col-md-3 col-sm-3 col-xs-5\">\n        <label class=\"control-label\">Difficulty:</label>\n      </div>\n      <div class=\"input-group col-md-9 col-sm-9 col-xs-7\">\n        <span class=\"text\">{{getDifficulty()}}</span>\n      </div>\n      <span class=\"help-block\"></span>\n    </div>\n\n  </div>\n\n  <div class=\"col-md-8\">\n    <div class=\"col-md-12\">\n      <div class=\"form-group col-md-12 col-sm-12 col-xs-12\">\n        <div class=\"col-md-3 col-sm-3 col-xs-5\">\n          <label class=\"control-label\">Question:</label>\n        </div>\n        <div class=\"col-md-9 col-sm-9 col-xs-7\">\n          <span class=\"text\">{{question.text}}</span>\n        </div>\n        <span class=\"help-block\"></span>\n      </div>\n    </div>\n\n\n    <div class=\"form-group col-md-12 col-sm-12 col-xs-12\">\n      <div class=\"col-md-3 col-sm-3 col-xs-6\">\n        <label class=\"col-md-12 control-label\">Answer:</label>\n      </div>\n      <div class=\"col-md-9 col-sm-9 col-xs-6\">\n        <div class=\"col-md-12\" *ngFor=\"let answer of question.answers\">\n          <div class=\"col-md-1 col-sm-1 col-xs-4\">\n            <span *ngIf=\"answer.correct\" class=\"fa fa-check-circle-o text-success\"></span>\n            <span *ngIf=\"answer.id === choiseanswersId && !answer.correct\" class=\"fa fa-times-circle text-danger\"></span>\n            <span *ngIf=\"!(answer.id === choiseanswersId) && !answer.correct \" class=\"fa fa-circle-o text-danger\"></span>\n          </div>\n          <div class=\"col-md-11 col-sm-11 col-xs-8\">\n            <span class=\"text\">{{answer.text}}</span>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n"

/***/ }),
/* 363 */
/***/ (function(module, exports) {

module.exports = "<br>\n<div class=\"row\">\n  <div class=\"col-md-6 col-sm-6 col-xs-12\">\n    <form>\n      <div class=\"form-group\">\n        <label class=\"col-md-3 control-label\">Filter Students:</label>\n        <div class=\"col-md-9\">\n          <div class=\"input-group\">\n            <!--<input type=\"text\" class=\"input-block-level form-control\" autocomplete=\"off\" placeholder=\"Search for student\">-->\n            <input type=\"text\" class=\"form-control\" autocomplete=\"off\" name=\"search\" id=\"search\" [(ngModel)]=\"model.search\" value=\"\" #search=\"ngModel\" placeholder=\"Search for student\"/>\n            <span class=\"input-group-addon\"><span class=\"fa fa-pencil\"></span></span>\n          </div>\n          <span class=\"help-block\"></span>\n        </div>\n      </div>\n    </form>\n  </div>\n</div>\n\n<!-- START RESPONSIVE TABLES -->\n<div class=\"panel panel-default\">\n\n  <div class=\"panel-heading\">\n    <h3 class=\"panel-title\">Results</h3>\n    <ul class=\"panel-controls\">\n      <li><a (click)=\"refreshExamResult()\" class=\"control-info\"><span class=\"glyphicon glyphicon-refresh\"></span></a></li>\n    </ul>\n  </div>\n\n  <div class=\"panel-body panel-body-table\">\n\n    <div class=\"table-responsive\">\n      <table class=\"table table-bordered table-striped table-actions\">\n        <thead>\n        <tr>\n          <th class=\"text-center\">Student</th>\n          <th class=\"text-center\" width=\"150\">Correct Answers</th>\n          <th class=\"text-center\" width=\"150\">Score</th>\n          <th class=\"text-center\" width=\"110\">Actions</th>\n        </tr>\n        </thead>\n        <tbody>\n\n        <tr *ngFor=\"let result of submissionResults | StudentsFilterResultAll: model.search\">\n          <td class=\"text-center\"><strong>{{result.user.getNameActive()}}</strong></td>\n          <td class=\"text-center\"><strong>{{result.correct}}/{{result.total}}</strong></td>\n          <td class=\"text-center\"><strong>{{result.score}}</strong></td>\n          <td>\n            <strong class=\"text-danger\" *ngIf=\"!result.id\" >No Submission</strong>\n            <button *ngIf=\"result.id\" [routerLink]=\"['/dashboard','classes',class_id,'groups',group_id,'exams',exam_id,'submission',result.id]\" type=\"button\" class=\"btn btn-primary btn-rounded btn-sm\">View Details</button>\n          </td>\n        </tr>\n\n        </tbody>\n      </table>\n    </div>\n\n  </div>\n</div>\n<!-- END RESPONSIVE TABLES -->\n\n\n"

/***/ }),
/* 364 */
/***/ (function(module, exports) {

module.exports = "<div class=\"row\">\n  <div class=\"col-md-4 col-md-offset-8 col-sm-6 col-sm-offset-6 col-xs-12\">\n    <div class=\"col-md-6 col-md-offset-6\">\n      <button (click)=\"goBack()\" type=\"button\" class=\"btn btn-primary btn-block\">Go Back</button>\n    </div>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-12\">\n    <div class=\"panel panel-default\">\n      <div class=\"panel-heading ui-draggable-handle\">\n        <h3 class=\"panel-title\">{{nameExam}} </h3>\n        <div class=\"pull-right\">\n          <label class=\"label-control\">Score:</label>\n          <span>{{scoreExam}}</span>\n        </div>\n      </div>\n      <div class=\"panel-body\">\n        <div class=\"panel-body\" >\n          <app-question-preview *ngFor=\"let question of questions; let i = index\" [choiseanswersId]=\"choiseanswersId[i]\" [question]=\"question\"></app-question-preview>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-4 col-md-offset-8 col-sm-6 col-sm-offset-6 col-xs-12\">\n    <div class=\"col-md-6 col-md-offset-6\">\n      <button (click)=\"goBack()\" type=\"button\" class=\"btn btn-primary btn-block\">Go Back</button>\n    </div>\n  </div>\n</div>\n\n"

/***/ }),
/* 365 */
/***/ (function(module, exports) {

module.exports = "<div class=\"row\">\n  <div class=\"col-md-4 col-md-offset-8 col-sm-6 col-sm-offset-6 col-xs-12\">\n    <div class=\"col-md-6\">\n      <button type=\"button\" (click)=\"saveEverything()\" class=\"btn btn-primary btn-block\">Save All</button>\n    </div>\n    <div class=\"col-md-6\">\n      <a [routerLink]=\"['/dashboard']\" ><button type=\"button\" class=\"btn btn-primary btn-block\">Terminate</button> </a>\n    </div>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-12\">\n    <div class=\"panel panel-default\">\n      <div class=\"panel-heading ui-draggable-handle\">\n        <h3 class=\"panel-title\">{{nameExam}} </h3>\n        <ul class=\"panel-controls\">\n        </ul>\n      </div>\n      <div class=\"panel-body\">\n        <div class=\"panel-body\" >\n          <app-question-submit *ngFor=\"let question of questions; let i = index\" [saveAll]=\"saveAll\" [submissionId]=\"submissionId\" [choiseanswersId]=\"choiseanswersId[i]\" [question]=\"question\"></app-question-submit>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-4 col-md-offset-8 col-sm-6 col-sm-offset-6 col-xs-12\">\n    <div class=\"col-md-6\">\n      <button type=\"button\" (click)=\"saveEverything()\" class=\"btn btn-primary btn-block\">Save All</button>\n    </div>\n    <div class=\"col-md-6\">\n      <a  [routerLink]=\"['/dashboard']\" > <button  type=\"button\" class=\"btn btn-primary btn-block\">Terminate</button> </a>\n    </div>\n  </div>\n</div>\n"

/***/ }),
/* 366 */
/***/ (function(module, exports) {

module.exports = "<br>\n\n<div class=\"row question-submit\">\n  <div class=\"col-md-12\">\n    <div class=\"col-md-10\">\n      <div class=\"col-md-12\">\n        <div class=\"form-group col-md-12 col-sm-12 col-xs-12\">\n          <div class=\"col-md-3 col-sm-3 col-xs-5\">\n            <label class=\"control-label\">Question:</label>\n          </div>\n          <div class=\"col-md-9 col-sm-9 col-xs-7\">\n            <span class=\"text\">{{question.text}}</span>\n          </div>\n          <span class=\"help-block\"></span>\n        </div>\n      </div>\n\n\n      <div class=\"form-group col-md-12 col-sm-12 col-xs-12\">\n        <div class=\"col-md-3 col-sm-3 col-xs-6\">\n          <label class=\"col-md-12 control-label\">Answer:</label>\n        </div>\n        <div class=\"col-md-9 col-sm-9 col-xs-6\">\n          <div class=\"col-md-12\" *ngFor=\"let answer of question.answers \">\n            <div class=\"col-md-1 col-sm-1 col-xs-4\">\n              <input [(ngModel)]=\"correctAnswer.correta\" #getname()=\"ngModel\" type=\"radio\" [value]=answer.id name=\"{{'name' + question.id}}\">\n            </div>\n            <div class=\"col-md-11 col-sm-11 col-xs-8\">\n              <span class=\"text\">{{answer.text }}</span>\n            </div>\n          </div>\n        </div>\n      </div>\n\n    </div>\n\n    <div class=\"col-md-2 pull-right\">\n      <div class=\"col-md-12 \">\n        <button class=\"btn btn-block\" [ngClass]=\"{  'btn-primary': !saveOk, 'btn-success': saveOk }\" (click)=\"saveAnswerNow()\" type=\"button\" >Save</button>\n      </div>\n    </div>\n  </div>\n\n</div>\n"

/***/ }),
/* 367 */
/***/ (function(module, exports) {

module.exports = "<br>\n<div class=\"row\">\n  <div class=\"col-lg-12 col-md-12\">\n    <div class=\"panel panel-default\">\n      <div class=\"panel-heading ui-draggable-handle\">\n        <h3 class=\"panel-title\"></h3>\n        <ul class=\"panel-controls\">\n          <li><a><span class=\"fa fa-edit\"></span></a></li>\n          <li><a class=\"panel-collapse\"><span class=\"fa fa-angle-down\"></span></a></li>\n        </ul>\n      </div>\n      <div class=\"panel-body\">\n        <div class=\"col-md-12\">\n          <div class=\"col-md-3 col-sm-3 col-xs-5\">\n            <label class=\"control-label\">Class:</label>\n          </div>\n          <div class=\"input-group col-md-9 col-sm-9 col-xs-7\">\n            <span class=\"text\">{{getClassName()}}</span>\n          </div>\n        </div>\n        <div class=\"col-md-12\">\n          <div class=\"col-md-3 col-sm-3 col-xs-5\">\n            <label class=\"control-label\">Group:</label>\n          </div>\n          <div class=\"input-group col-md-9 col-sm-9 col-xs-7\">\n            <span class=\"text\">{{getGroupName()}}</span>\n          </div>\n        </div>\n        <div class=\"col-md-12\">\n          <div class=\"col-md-3 col-sm-3 col-xs-5\">\n            <label class=\"control-label\">Teacher:</label>\n          </div>\n          <div class=\"input-group col-md-9 col-sm-9 col-xs-7\">\n            <span class=\"text\">{{getTeacher()}}</span>\n          </div>\n        </div>\n        <div class=\"col-md-12\">\n          <div class=\"col-md-3 col-sm-3 col-xs-5\">\n            <label class=\"control-label\">Date:</label>\n          </div>\n          <div class=\"input-group col-md-9 col-sm-9 col-xs-7\">\n            <span class=\"text\">{{exam.beginDate | date:'EEEE, d MMMM y m:j' }} </span>\n          </div>\n        </div>\n        <div class=\"col-md-12\">\n          <div class=\"col-md-3 col-sm-3 col-xs-5\">\n            <label class=\"control-label\">Duration:</label>\n          </div>\n          <div class=\"input-group col-md-9 col-sm-9 col-xs-7\">\n            <span class=\"text\">{{exam.duration}} minutes </span>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n\n<div class=\"row\" *ngIf=\"isTeacher()\">\n  <div class=\"col-lg-12 col-md-12\">\n    <div class=\"panel panel-default\">\n      <div class=\"panel-heading ui-draggable-handle\">\n        <h3 class=\"panel-title\">Exam Preview</h3>\n        <ul class=\"panel-controls\">\n        </ul>\n      </div>\n      <div class=\"panel-body\">\n        <div class=\"row\">\n          <app-question-preview class=\"border-group\" *ngFor=\"let question of questions\" [question]=\"question\"></app-question-preview>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n"

/***/ }),
/* 368 */
/***/ (function(module, exports) {

module.exports = "<div class=\"row\">\n  <div class=\"col-md-12\">\n    <h2 class=\"title-line\">Create Group</h2>\n  </div>\n</div>\n\n<br>\n<form class=\"form-horizontal\" (ngSubmit)=\"f_createGroup.form.valid && saveGroup()\" #f_createGroup=\"ngForm\" novalidate>\n<div class=\"row\">\n  <div class=\"col-md-12 col-xs-12\">\n    <div class=\"pull-right\">\n      <button type=\"submit\" class=\"btn btn-primary\">Save Group</button>\n    </div>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-6 col-sm-6 col-xs-12\">\n      <div class=\"form-group\">\n        <label class=\"col-md-3 control-label\">Group Name:</label>\n        <div class=\"col-md-9\">\n          <div class=\"input-group\" [ngClass]=\"{ 'has-error': nameGroupValid }\">\n            <span class=\"input-group-addon\"><span class=\"fa fa-pencil\"></span></span>\n            <input type=\"text\" class=\"form-control\" name=\"nameGroup\" [(ngModel)]=\"groupCreate.nameGroup\" placeholder=\"Group Name\" #nameGroup=\"ngModel\"/>\n          </div>\n          <div *ngIf=\"nameGroupValid\" class=\"help-block\">Group name is required</div>\n          <label *ngIf=\"groupAlreadyExists\"  class=\"control-label groupAlreadyExists\">Group already exists </label>\n        </div>\n      </div>\n\n      <div class=\"form-group\">\n        <label class=\"col-md-3 control-label\">Invite Students:</label>\n        <div class=\"col-md-9 col-xs-12\">\n          <textarea class=\"form-control\" rows=\"5\" name=\"students\" [(ngModel)]=\"groupCreate.students\" #students=\"ngModel\"></textarea>\n          <span class=\"help-block\">Separators: line , ;</span>\n          <span class=\"help-block\">Example: ana@gmail.com,rui@gmail.com\n            daniela@hotmail.com;pedro@gmail.com</span>\n        </div>\n      </div>\n\n      <div class=\"col-md-12\">\n        <button (click)=\"treatmentEmail()\" type=\"button\" class=\"btn btn-primary pull-right\">Add Students</button>\n      </div>\n  </div>\n\n  <div class=\"col-md-6 col-sm-6 col-xs-12\">\n\n    <div class=\"panel panel-default\">\n      <div class=\"panel-heading ui-draggable-handle\">\n        <h3 class=\"panel-title\">Invited Students </h3>\n        <ul class=\"panel-controls\">\n          <li><a class=\"panel-collapse\"><span class=\"fa fa-angle-down\"></span></a></li>\n        </ul>\n      </div>\n      <div class=\"panel-body\">\n        <!-- Search form -->\n        <form role=\"form\">\n          <div class=\"bootstrap-select-searchbox\">\n            <input type=\"text\" class=\"input-block-level form-control\" name=\"search\" id=\"search\" [(ngModel)]=\"model.search\" value=\"\" #search=\"ngModel\" autocomplete=\"off\" placeholder=\"Search for student\">\n          </div>\n        </form>\n\n        <div class=\"panel-body scroll list-students\" >\n          <!-- Coloquei o div externo porque penso que ajuda a fazer o ngFor caso contrario tirar-->\n          <ul *ngFor=\"let student of allStudentsOfGroup | StudentsFilter: model.search\" class=\"list-group border-bottom \">\n            <li class=\"list-group-item\">{{student}}\n              <span (click)=\"removeStudent(student)\" class=\"glyphicon glyphicon-remove pull-right text-danger\"></span>\n            </li>\n          </ul>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-12 col-xs-12\">\n    <div class=\"pull-right\">\n      <button type=\"submit\" class=\"btn btn-primary\">Save Group</button>\n    </div>\n  </div>\n</div>\n</form>\n"

/***/ }),
/* 369 */
/***/ (function(module, exports) {

module.exports = "<br>\n<div class=\"row\">\n  <div class=\"col-md-2 pull-right\">\n    <button (click)=\"exportResults()\" class=\"btn btn-block btn-primary \">\n      <span class=\"fa fa-eye\"></span>Export to csv\n    </button>\n  </div>\n  <div class=\"col-md-6 col-sm-6 col-xs-12\">\n    <form role=\"form\">\n      <div class=\"form-group\">\n        <div class=\"input-group\">\n          <!--<input type=\"text\" class=\"input-block-level form-control\" autocomplete=\"off\" placeholder=\"Search for student\">-->\n          <input type=\"text\" class=\"form-control\" autocomplete=\"off\" name=\"search\" id=\"search\" [(ngModel)]=\"model.search\" value=\"\" #search=\"ngModel\" placeholder=\"Search for student\"/>\n          <span class=\"input-group-addon\"><span class=\"fa fa-pencil\"></span></span>\n        </div>\n      </div>\n    </form>\n  </div>\n</div>\n\n<br>\n<div class=\"row\">\n  <div class=\"col-md-12\">\n    <!-- START RESPONSIVE TABLES -->\n    <div class=\"panel panel-default\">\n      <div class=\"panel-body panel-body-table\">\n\n        <div class=\"table-responsive\">\n          <table class=\"table table-bordered table-striped table-actions\">\n            <thead>\n            <tr>\n              <th class=\"text-center\" *ngFor=\"let test of header\">{{test}}</th>\n            </tr>\n            </thead>\n            <tbody>\n\n            <!-- Ter em atenção aqui inverter a ordem de inserção para mostrar os mais recentes primeiro-->\n            <tr *ngFor=\"let student of studentsScores | StudentsFilterGroup: model.search\">\n              <td class=\"text-center\"><strong>{{student.name}}</strong></td>\n              <td class=\"text-center\"><strong>{{student.email}}</strong></td>\n              <th class=\"text-center\" *ngFor=\"let score of student.exams\">{{score}}</th>\n            </tr>\n\n            </tbody>\n          </table>\n        </div>\n\n      </div>\n    </div>\n    <!-- END RESPONSIVE TABLES -->\n  </div>\n</div>\n"

/***/ }),
/* 370 */
/***/ (function(module, exports) {

module.exports = "<div class=\"row\">\n  <div class=\"col-md-12\">\n    <h2 class=\"title-line\">Group {{getGroupName()}}</h2>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-lg-6 col-md-6 col-lg-offset-6 col-md-offset-6\" *ngIf=\"isTeacher()\">\n    <form class=\"form-horizontal\" role=\"form\">\n      <div class=\"col-lg-4 col-md-4 col-sm-4 col-xs-12\">\n        <button (click)=\"ClickButton()\" class=\"btn btn-block btn-danger \">\n          <span class=\"fa fa-minus-square-o\"></span>Group\n        </button>\n      </div>\n      <div class=\"col-lg-4 col-md-4 col-sm-4 col-xs-12\">\n        <button [routerLink]=\"['/dashboard','classes',classId, 'groups', groupId,'results']\" class=\"btn btn-block btn-primary \">\n          <span class=\"fa fa-eye\"></span>Result\n        </button>\n      </div>\n      <div class=\"col-lg-4 col-md-4 col-sm-4 col-xs-12\">\n        <button [routerLink]=\"['/dashboard','classes',classId,'groups',groupId,'exams','new']\"class=\"btn btn-block btn-primary \">\n          <span class=\"fa fa-plus-square\"></span>Exam\n        </button>\n      </div>\n\n    </form>\n  </div>\n</div>\n\n<br>\n\n<div class=\"col-md-8\">\n  <!-- START RESPONSIVE TABLES -->\n  <div class=\"panel panel-default\">\n\n    <div class=\"panel-heading\">\n      <h3 class=\"panel-title\">Ongoiing/UpComing</h3>\n      <ul class=\"panel-controls\">\n        <li><a class=\"panel-collapse\"><span class=\"fa fa-angle-down\"></span></a></li>\n      </ul>\n    </div>\n\n    <div class=\"panel-body panel-body-table\">\n\n      <div class=\"table-responsive\">\n        <table class=\"table table-bordered table-striped table-actions\">\n          <thead>\n          <tr>\n            <th class=\"text-center\">Name</th>\n            <th class=\"text-center\">Class</th>\n            <th class=\"text-center\">Group</th>\n            <th class=\"text-center\">Teacher</th>\n            <th class=\"text-center\" width=\"150\">Date</th>\n          </tr>\n          </thead>\n          <tbody>\n\n\n          <!-- Ter em atenção aqui inverter a ordem de inserção para mostrar os mais recentes primeiro-->\n          <tr *ngFor=\"let onGoing of onGoingExams.reverse()\" (click)=\"goToExamOnGoing(onGoing)\">\n            <td class=\"text-center\"><strong>{{onGoing.name}}</strong></td>\n            <td class=\"text-center\"><strong>{{onGoing.group.class.name}}</strong></td>\n            <td class=\"text-center\"><strong>{{onGoing.group.name}}</strong></td>\n            <td class=\"text-center\"><strong>{{onGoing.group.class.user.getName()}}</strong></td>\n            <td class=\"text-center\"><strong>{{onGoing.beginDate | date:'dd-MM-yyyy HH:mm'}}</strong></td>\n          </tr>\n\n\n          <!-- Ter em atenção aqui inverter a ordem de inserção para mostrar os mais recentes primeiro-->\n          <tr *ngFor=\"let upComing of upComingExams.reverse()\" [routerLink]=\"['/dashboard','classes',upComing.group.class.id,'groups',upComing.group.id,'exams',upComing.id]\">\n            <td class=\"text-center\"><strong>{{upComing.name}}</strong></td>\n            <td class=\"text-center\"><strong>{{upComing.group.class.name}}</strong></td>\n            <td class=\"text-center\"><strong>{{upComing.group.name}}</strong></td>\n            <td class=\"text-center\"><strong>{{upComing.group.class.user.getName()}}</strong></td>\n            <td class=\"text-center\"><strong>{{upComing.beginDate | date:'dd-mm-yyyy HH:mm'}}</strong></td>\n          </tr>\n\n\n          </tbody>\n        </table>\n      </div>\n\n    </div>\n  </div>\n  <!-- END RESPONSIVE TABLES -->\n\n  <!-- START RESPONSIVE TABLES -->\n  <div class=\"panel panel-default\">\n\n    <div class=\"panel-heading\">\n      <h3 class=\"panel-title\">History</h3>\n      <ul class=\"panel-controls\">\n        <li><a class=\"panel-collapse\"><span class=\"fa fa-angle-down\"></span></a></li>\n      </ul>\n    </div>\n\n    <div class=\"panel-body panel-body-table\">\n\n      <div class=\"table-responsive\">\n        <table class=\"table table-bordered table-striped table-actions\">\n          <thead>\n          <tr>\n            <th class=\"text-center\">Name</th>\n            <th class=\"text-center\">Class</th>\n            <th class=\"text-center\">Group</th>\n            <th class=\"text-center\">Teacher</th>\n            <th class=\"text-center\" width=\"150\">Date</th>\n          </tr>\n          </thead>\n          <tbody>\n          <!-- Ter em atenção aqui inverter a ordem de inserção para mostrar os mais recentes primeiro-->\n          <tr *ngFor=\"let historyExam of historyExams\" (click)=\"goToExamResult(historyExam)\">\n            <td class=\"text-center\"><strong>{{historyExam.name}}</strong></td>\n            <td class=\"text-center\"><strong>{{historyExam.group.class.name}}</strong></td>\n            <td class=\"text-center\"><strong>{{historyExam.group.name}}</strong></td>\n            <td class=\"text-center\"><strong>{{historyExam.group.class.user.getName()}}</strong></td>\n            <td class=\"text-center\"><strong>{{historyExam.beginDate | date:'dd-mm-yyyy HH:mm'}}</strong></td>\n          </tr>\n\n          </tbody>\n        </table>\n      </div>\n\n    </div>\n  </div>\n  <!-- END RESPONSIVE TABLES -->\n</div>\n\n<div class=\"col-md-4\">\n  <!-- Search form -->\n  <div class=\"panel panel-default\">\n    <div class=\"panel-heading ui-draggable-handle\">\n      <h3 class=\"panel-title\">Students</h3>\n      <ul class=\"panel-controls\">\n        <li *ngIf=\"isTeacher()\" (click)=\"addStudent()\"><a><span class=\"fa fa-plus\"></span></a></li>\n        <li><a class=\"panel-collapse\"><span class=\"fa fa-angle-down\"></span></a></li>\n      </ul>\n      <div class=\"row\">\n        <div class=\"col-md-12 col-sm-12 col-xs-12 filter-group-students\">\n          <form>\n            <div class=\"form-group\">\n              <input type=\"text\" class=\"input-block-level form-control\" name=\"search\" id=\"search\" [(ngModel)]=\"model.search\" value=\"\" #search=\"ngModel\" autocomplete=\"off\" placeholder=\"Search for student\">\n            </div>\n          </form>\n        </div>\n      </div>\n    </div>\n    <div class=\"panel-body scroll list\">\n      <ul *ngFor=\"let student of allStudentsOfGroup | StudentsFilterGroup: model.search\" class=\"list-group border-bottom\">\n        <li class=\"list-group-item\">{{student.email}}\n          <span *ngIf=\"isTeacher()\" (click)=\"removeStudent(student.id)\" class=\"glyphicon glyphicon-remove pull-right text-danger\"></span>\n        </li>\n      </ul>\n    </div>\n  </div>\n</div>\n\n<!--<popup #popup1 (confirmClick)=\"confirmDelete()\" (cancelClick)=\"cancelDelete()\">-->\n  <!--Are you sure you want to delete the group?-->\n<!--</popup>-->\n"

/***/ }),
/* 371 */
/***/ (function(module, exports) {

module.exports = "<div class=\"row\">\n  <router-outlet></router-outlet>\n</div>\n"

/***/ }),
/* 372 */
/***/ (function(module, exports) {

module.exports = "<div class=\"col-md-1 col-sm-1 col-xs-4\">\n  <input (click)=\"getValue()\" type=\"radio\" value=i name=\"checked\">\n</div>\n<div class=\"col-md-11 col-sm-11 col-xs-8\" [ngClass]=\"{ 'has-error': saveanswer && !answerText.valid }\">\n  <textarea name=\"answerText\" #answerText=\"ngModel\" [(ngModel)]=\"answe.answerText\" type=\"text\" class=\"form-control\" rows=\"2\" required></textarea>\n  <span class=\"help-block\"></span>\n  <div *ngIf=\"!answerText.valid && saveanswer\" class=\"help-block\">Answer is required</div>\n</div>\n\n<span *ngIf=\"oldViewRemoveAnswer\" (click)=\"removeAnserFromQuestion()\" class=\"glyphicon glyphicon-remove pull-right text-danger\"></span>\n"

/***/ }),
/* 373 */
/***/ (function(module, exports) {

module.exports = "<span (click)=\"removeQuestion()\" class=\"glyphicon glyphicon-remove pull-right text-danger\"></span>\n<br>\n<div class=\"row border-group\">\n  <form class=\"form-horizontal\" (ngSubmit)=\"f_createQuestion.form.valid && saveQuestion()\" #f_createQuestion=\"ngForm\" novalidate>\n    <div class=\"col-md-4 pull-right\">\n      <div class=\"form-group col-md-12 col-sm-12 col-xs-12\">\n        <div class=\"col-md-3 col-sm-3 col-xs-5\">\n          <label class=\"control-label\">Category:</label>\n        </div>\n        <div class=\"input-group col-md-9 col-sm-9 col-xs-7\" [ngClass]=\"{ 'has-error': f_createQuestion.submitted && !category.valid }\">\n          <input type=\"text\" name=\"category\" [(ngModel)]=\"quest.category\" #category=\"ngModel\" value=\"\" class=\"form-control\" autocomplete=\"off\" list=\"categories\" required>\n          <span class=\"input-group-addon\"><span class=\"fa fa-pencil\"></span></span>\n          <datalist id=\"categories\">\n            <option *ngFor=\"let categorie of allCategories\" value=\"{{categorie}}\">\n          </datalist>\n          <span class=\"help-block\"></span>\n        </div>\n        <div *ngIf=\"f_createQuestion.submitted && !category.valid\" class=\"help-block\">First Name is required</div>\n      </div>\n\n\n      <div class=\"form-group col-md-12 col-sm-12 col-xs-12\">\n        <div class=\"col-md-3 col-sm-3 col-xs-5\">\n          <label class=\"control-label\">Dificulty:</label>\n        </div>\n        <div class=\"input-group col-md-9 col-sm-9 col-xs-7\">\n          <select name=\"refresh\" id=\"dificulty\" [(ngModel)]=\"quest.dificulty\" #dificulty=\"ngModel\" class=\"form-control\">\n            <option value=\"1\">Easy</option>\n            <option value=\"2\">Normal</option>\n            <option value=\"3\">Hard</option>\n          </select>\n          <span class=\"input-group-addon\"><span class=\"fa fa-pencil\"></span></span>\n        </div>\n        <span class=\"help-block\"></span>\n      </div>\n\n    </div>\n\n    <div class=\"col-md-8\">\n      <div class=\"col-md-12\">\n        <div class=\"form-group col-md-12 col-sm-12 col-xs-12\">\n          <div class=\"col-md-3 col-sm-3 col-xs-5\">\n            <label class=\"control-label\">Question:</label>\n          </div>\n          <div class=\"col-md-9 col-sm-9 col-xs-7\" [ngClass]=\"{ 'has-error': f_createQuestion.submitted && !questionText.valid }\">\n            <textarea name=\"questionText\" #questionText=\"ngModel\" [(ngModel)]=\"quest.questionText\" type=\"text\" class=\"form-control\" rows=\"2\" required></textarea>\n            <span class=\"help-block\"></span>\n            <div *ngIf=\"f_createQuestion.submitted && !questionText.valid\" class=\"help-block\">Question is required</div>\n          </div>\n        </div>\n      </div>\n\n\n      <div class=\"col-md-offset-4 col-lg-6 col-md-6 col-sm-6 col-xs-12 \">\n        <div *ngIf=\"needSelectRadio\" class=\"alert alert-danger\" role=\"alert\">\n          <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n          <strong>Need to select a answer!</strong>\n        </div>\n      </div>\n\n      <div class=\"form-group col-md-12 col-sm-12 col-xs-12\">\n        <div class=\"col-md-3 col-sm-3 col-xs-6\">\n          <label class=\" col-md-12 control-label\">Answer:</label>\n        </div>\n        <div class=\"col-md-9 col-sm-9 col-xs-6\">\n          <app-answer-create *ngFor=\" let answer of answers; let i = index\" [i]=i [answer]=\"answer\" [saveanswer]=\"saveanswer\" (removeAnswer)=\"removeAnswerFromQuestion($event)\" [changeSave]=\"changeSave\" [viewRemoveAnswer]=\"removeAnswer\" (clickRadio)=\"correctAnswer($event)\"></app-answer-create>\n          <div class=\"col-md-6 col-md-offset-6\">\n            <button (click)=\"addAnswer()\" class=\"btn btn-block btn-primary \">\n              <span class=\"fa fa-plus-square\"></span>Add Answer\n            </button>\n          </div>\n        </div>\n      </div>\n    </div>\n\n    <div class=\"row\">\n      <div class=\"col-md-12 col-sm-12 col-xs-12\">\n        <div class=\"col-md-3 pull-right\">\n          <button type=\"submit\" class=\"btn btn-success btn-block\">Save</button>\n        </div>\n      </div>\n    </div>\n\n  </form>\n</div>\n"

/***/ }),
/* 374 */
/***/ (function(module, exports) {

module.exports = "<br>\n\n<div *ngIf=\"questionsAddOk\" class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12 \">\n  <div class=\"alert alert-success\" role=\"alert\">\n    <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n    <strong>Questions add!</strong>\n  </div>\n</div>\n<div *ngIf=\"justOnQuestion\" class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12 \">\n  <div class=\"alert alert-success\" role=\"alert\">\n    <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n    <strong>One Question Add!</strong>\n  </div>\n</div>\n\n<div class=\"row\">\n  <div class=\"col-md-3 pull-right\">\n    <button (click)=\"saveQuestion()\" type=\"button\" class=\"btn btn-success btn-block\">Save All</button>\n  </div>\n</div>\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-12\">\n    <div class=\"panel panel-default\">\n      <div class=\"panel-heading ui-draggable-handle\">\n        <h3 class=\"panel-title\">Question Create</h3>\n        <ul class=\"panel-controls\">\n          <li><a (click)=\"moreQuestion()\"><span class=\"fa fa-plus\"></span></a></li>\n        </ul>\n      </div>\n      <div class=\"panel-body\">\n        <div class=\"panel-body\" >\n          <app-question-create-item *ngFor=\"let question of questions\" [classId]=\"classId\" [question]=\"question\" [allCategories]=\"categories\" [saveAll]=\"saveAll\" (questionPostOK)=\"questionPostOk($event)\" (removeQuestio)=\"removeQuestion()\" (saveQuestionButton)=\"saveQuestionButton($event)\"></app-question-create-item>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n"

/***/ }),
/* 375 */
/***/ (function(module, exports) {

module.exports = "<br>\n<div class=\"row\">\n  <div class=\"col-md-12\">\n    <div class=\"form-group col-md-6 col-sm-6 col-xs-12\">\n      <div class=\"col-md-3 col-sm-3 col-xs-5\">\n        <label class=\"control-label\">Category:</label>\n      </div>\n      <div class=\"input-group col-md-9 col-sm-9 col-xs-7\">\n        <select (ngModelChange)=\"getQuestion()\"  class=\"form-control select\" name=\"category\" [(ngModel)]=\"questionModel.category\" required>\n          <option *ngFor=\"let categorie of categoriesName\" [value]=\"categorie\">{{categorie}}</option>\n        </select>\n        <span class=\"input-group-addon\"><span class=\"fa fa-pencil\"></span></span>\n      </div>\n      <span class=\"help-block\"></span>\n    </div>\n\n\n    <div class=\"form-group col-md-6 col-sm-6 col-xs-12\">\n      <div class=\"col-md-3 col-sm-3 col-xs-5\">\n        <label class=\"control-label\">Dificulty:</label>\n      </div>\n      <div class=\"input-group col-md-9 col-sm-9 col-xs-7\">\n        <select  (ngModelChange)=\"getQuestion()\" class=\"form-control select\" name=\"difi\" [(ngModel)]=\"questionModel.difi\" required>\n          <option *ngFor=\"let dificult of dificultys\" [value]=\"dificult.value\" >{{dificult.dificulty}}</option>\n        </select>\n        <span class=\"input-group-addon\"><span class=\"fa fa-pencil\"></span></span>\n      </div>\n      <span class=\"help-block\"></span>\n    </div>\n\n  </div>\n\n</div>\n\n<div class=\"row\">\n  <div class=\"col-lg-12 col-md-12\">\n    <div class=\"panel panel-default\">\n      <div class=\"panel-heading ui-draggable-handle\">\n        <h3 class=\"panel-title\">Questions</h3>\n        <ul class=\"panel-controls\">\n        </ul>\n      </div>\n      <div class=\"panel-body\">\n        <div class=\"row\">\n          <app-question-preview class=\"border-group\" *ngFor=\"let question of questions\" [question]=\"question\"></app-question-preview>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n\n\n\n\n\n"

/***/ }),
/* 376 */
/***/ (function(module, exports) {

module.exports = "<div class=\"page-container page-navigation-top-fixed\">\n\n\n  <!-- START PAGE SIDEBAR -->\n  <div class=\"page-sidebar\" >\n    <!-- START X-NAVIGATION -->\n    <ul class=\"x-navigation\">\n      <li class=\"xn-logo\">\n        <a [routerLink]=\"['/dashboard']\">Evaluation</a>\n        <a href=\"#page-top\" class=\"x-navigation-control\"></a>\n      </li>\n      <li class=\"xn-profile\" [routerLink]=\"['/dashboard','profile']\">\n        <div class=\"profile\">\n          <div class=\"profile-image\">\n            <img src=\"../assets/images/users/no-image.jpg\"/>\n          </div>\n          <div class=\"profile-data\">\n            <div class=\"profile-data-name\">{{this.getUserName()}}</div> <!-- Name of user-->\n            <div class=\"profile-data-title\">{{this.getUserType()}}</div> <!-- Type of user-->\n          </div>\n        </div>\n      </li>\n      <!-- FIXME Ver isto melhor-->\n      <li class=\"xn-title\">Exams OnGoing</li>\n      <li *ngFor=\"let exam of examsOnGoing\">\n        <a *ngIf=\"isStudent()\" [routerLink]=\"['/dashboard','classes',exam.group._class.id,'groups',exam.group.id,'exams',exam.id,'submit']\"><span class=\"fa fa-desktop text-danger\"></span> <span class=\"xn-text text-danger\">{{exam.name}}</span></a>\n        <a *ngIf=\"isTeacher()\" [routerLink]=\"['/dashboard','classes',exam.group._class.id,'groups',exam.group.id,'exams',exam.id]\"><span class=\"fa fa-desktop text-danger\"></span> <span class=\"xn-text text-danger\">{{exam.name}}</span></a>\n      </li>\n\n\n      <li class=\"xn-title\">Navigation\n        <span (click)=\"updateNavbar()\" class=\"pull-right fa fa-refresh\"></span>\n      </li>\n\n      <li *ngFor=\"let col_node of collapse_struture; let col_node_pos = index\" [ngClass]=\"{'xn-openable': col_node.children, 'active': col_node.isCollapsed}\" >\n        <a (click)=\"navigateRoute(col_node.route, col_node, [col_node.id])\" ><span class=\"fa fa-desktop\"></span> <span class=\"xn-text\">{{col_node.name}}</span></a>\n\n        <!-- Classes -->\n        <ul *ngIf=\"col_node.children\">\n          <li *ngFor=\"let col_node_class of col_node.children; let col_node_class_pos = index\" [ngClass]=\"{'xn-openable': col_node_class.children , 'active': col_node_class.isCollapsed}\">\n            <a (click)=\"navigateRoute( col_node_class.route, col_node_class, [col_node.id,col_node_class.id])\" >{{col_node_class.name}}</a>\n\n            <!-- Group -->\n            <ul *ngIf=\"col_node_class.children\">\n              <li *ngFor=\"let col_node_group of col_node_class.children; let col_node_group_pos = index\" >\n                <a (click)=\"navigateRoute( col_node_group.route, col_node_group, [col_node.id,col_node_class.id, col_node_group.id ])\" >{{col_node_group.name}}</a>\n              </li>\n            </ul>\n            <!-- ./Group -->\n\n          </li>\n        </ul>\n        <!-- ./Classes -->\n      </li>\n\n\n\n    </ul>\n    <!-- END X-NAVIGATION -->\n  </div>\n  <!-- END PAGE SIDEBAR -->\n\n  <!-- PAGE CONTENT -->\n  <div class=\"page-content\">\n\n\n    <ul class=\"x-navigation x-navigation-horizontal x-navigation-panel \">\n      <!-- TOGGLE NAVIGATION -->\n      <li class=\"xn-icon-button\">\n        <a (click)=\"toggledPageNavigation()\" class=\"x-navigation-minimize\"><span class=\"fa fa-dedent\"></span></a>\n      </li>\n      <!-- END TOGGLE NAVIGATION -->\n\n      <li class=\"xn-text\">\n        <a>{{nameInToggleNavigation}}</a>\n      </li>\n\n      <!-- SIGN OUT -->\n      <li class=\"xn-icon-button pull-right\">\n        <a (click)=\"logout()\" class=\"mb-control\" ><span class=\"fa fa-sign-out\"></span></a>\n      </li>\n      <!-- END SIGN OUT -->\n      <li [routerLink]=\"['/dashboard','notifications']\" class=\"xn-icon-button pull-right\" *ngIf=\"!isTeacher()\">\n        <a><span class=\"fa fa-tasks\"></span></a>\n        <div *ngIf=\"notifications.length>0\"class=\"informer informer-danger\">{{notifications.length}}</div>\n      </li>\n    </ul>\n\n    <div class=\"page-content-wrap\">\n      <br>\n      <div class=\"container\">\n        <router-outlet></router-outlet>\n      </div>\n    </div>\n\n  </div>\n</div>\n\n"

/***/ }),
/* 377 */
/***/ (function(module, exports) {

module.exports = "<!--Add Class -->\n  <div class=\"row\" *ngIf=\"isTeacher()\" >\n    <div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12 \">\n      <div *ngIf=\"classAsCreate\" class=\"alert alert-success\" role=\"alert\">\n        <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n        <strong>Class was created!</strong>\n      </div>\n    </div>\n    <div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12 \">\n      <form class=\"form-horizontal\" (ngSubmit)=\"f_newClass.form.valid && addClass()\" #f_newClass=\"ngForm\" novalidate>\n\n          <div class=\"abbreviation form-group col-lg-3 col-sm-3\" [ngClass]=\"{ 'has-error': f_newClass.submitted && !abbrev.valid }\">\n            <input type=\"text\" class=\"form-control\" name=\"abbrev\" [(ngModel)]=\"new_class_add.abbrev\" placeholder=\"Abbreviation\" #abbrev=\"ngModel\" required/>\n            <div *ngIf=\"f_newClass.submitted && !abbrev.valid\" class=\"help-block\">Abbreviation is required</div>\n          </div>\n\n          <div class=\"form-group col-md-6 col-sm-6\" [ngClass]=\"{ 'has-error': f_newClass.submitted && !nameClass.valid }\">\n            <input (click)=\"changeAlreadyExists()\" type=\"text\" class=\"form-control\" name=\"nameClass\" [(ngModel)]=\"new_class_add.nameClass\" placeholder=\"Class Name\" #nameClass=\"ngModel\" required/>\n            <div *ngIf=\"f_newClass.submitted && !nameClass.valid\" class=\"help-block\">New Class is required</div>\n            <label *ngIf=\"classAlreadyExists && nameClass.valid\"  class=\"control-label classAlreadyExists\">Class already exists</label>\n            <!--label *ngIf=\"classAsCreate && nameClass.valid\"  class=\"control-label classCreated\">Class was created </label-->\n          </div>\n\n          <div class=\"col-md-3 col-sm-3\">\n            <button class=\"btn btn-primary btn-block\" type=\"submit\">New Class</button>\n          </div>\n      </form>\n    </div>\n  </div>\n<!--Add Class -->\n\n<br>\n\n<!-- UpComing and History Exames -->\n<div class=\"row\">\n  <app-list-exame></app-list-exame>\n</div>\n<!-- UpComing and History Exames -->\n\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-12\">\n    <app-list-class></app-list-class>\n  </div>\n</div>\n\n\n\n"

/***/ }),
/* 378 */
/***/ (function(module, exports) {

module.exports = "<!-- START RESPONSIVE TABLES -->\n  <div class=\"panel panel-default\">\n\n    <div class=\"panel-heading\">\n      <h3 class=\"panel-title\">Groups</h3>\n    </div>\n\n    <div class=\"panel-body panel-body-table\">\n\n      <div class=\"table-responsive\">\n        <table class=\"table table-bordered table-striped table-actions\">\n          <thead>\n          <tr>\n            <th class=\"text-center\" width=\"200\">Abbreviation</th>\n            <th class=\"text-center\">Name</th>\n            <th class=\"text-center\" width=\"200\">Group</th>\n            <th class=\"text-center\" width=\"110\">Actions</th>\n          </tr>\n          </thead>\n          <tbody>\n\n          <!-- Ter em atenção aqui inverter a ordem de inserção para mostrar os mais recentes primeiro-->\n          <tr *ngFor=\"let group of allGroups\" >\n              <td class=\"text-center\" [routerLink]=\"['/dashboard','classes', group.class.id,'groups',group.id]\"><strong>{{group.class.abbreviation}}</strong></td>\n              <td class=\"text-center\" [routerLink]=\"['/dashboard','classes', group.class.id,'groups',group.id]\"><strong>{{group.class.name}}</strong></td>\n              <td class=\"text-center\" [routerLink]=\"['/dashboard','classes', group.class.id,'groups',group.id]\"><strong>{{group.name}}</strong></td>\n              <td>\n                <!-- FIXME implementar o edit do grupo -->\n                <button disabled=\"true\" class=\"btn btn-default btn-rounded btn-sm\" (click)=\"editGroup(group.id);\"><span class=\"fa fa-pencil\"></span></button>\n                <button class=\"btn btn-danger btn-rounded btn-sm\" (click)=\"deleteGroup(group.id);\"><span class=\"fa fa-times\"></span></button>\n              </td>\n          </tr>\n          <!-- Apagar depois -->\n\n          </tbody>\n        </table>\n      </div>\n\n    </div>\n  </div>\n<!-- END RESPONSIVE TABLES -->\n"

/***/ }),
/* 379 */
/***/ (function(module, exports) {

module.exports = "<div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12\">\n\n  <!-- NEWS WIDGET -->\n  <div class=\"panel panel-default\" >\n    <div class=\"panel-heading\">\n      <h3 class=\"panel-title\">Up Comming</h3>\n      <ul class=\"panel-controls\">\n        <li><a (click)=\"refreshUpComing()\" class=\"control-info\"><span class=\"glyphicon glyphicon-refresh\"></span></a></li>\n        <li><a class=\"panel-collapse\"><span class=\"fa fa-angle-down\"></span></a></li>\n      </ul>\n    </div>\n    <div class=\"panel-body scroll list-exames\" >\n      <!-- Coloquei o div externo porque penso que ajuda a fazer o ngFor caso contrario tirar-->\n      <div *ngFor=\"let exam of upComingExams.reverse()\" [routerLink]=\"['/dashboard', 'classes', exam.group.class.id]\">\n        <h6>{{exam.name}}</h6>\n        <p>\n          {{exam.group.class.name}} - {{exam.group.name}} - {{exam.group.class.user.firstName}} {{exam.group.class.user.lastName}}\n          <span style=\"margin-right: 5px\" class=\"text-muted pull-right\"><i class=\"fa fa-clock-o\"></i> {{exam.beginDate | date:'EEEE, d MMMM y hh:mm a'}} </span>\n        </p>\n      </div>\n\n    </div>\n  </div>\n  <!-- END NEWS WIDGET -->\n\n</div>\n\n<div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12\">\n\n  <!-- NEWS WIDGET -->\n  <div class=\"panel panel-default\">\n    <div class=\"panel-heading\">\n      <h3 class=\"panel-title\">History</h3>\n      <ul class=\"panel-controls\">\n        <li><a (click)=\"refreshHistory()\" class=\"control-info\"><span class=\"glyphicon glyphicon-refresh\"></span></a></li>\n        <li><a class=\"panel-collapse\"><span class=\"fa fa-angle-down\"></span></a></li>\n      </ul>\n    </div>\n    <div class=\"panel-body scroll list-exames\">\n      <!-- Coloquei o div externo porque penso que ajuda a fazer o ngFor caso contrario tirar-->\n      <div *ngFor=\"let exam of historyExams\" (click)=\"goToExamResult(exam)\">\n        <h6>{{exam.name}}</h6>\n        <p>\n          {{exam.group.class.name}} - {{exam.group.name}} - {{exam.group.class.user.firstName}} {{exam.group.class.user.lastName}}\n          <span style=\"margin-right: 5px\" class=\"text-muted pull-right\"><i class=\"fa fa-clock-o\"></i> {{exam.beginDate | date:'EEEE, d MMMM y hh:mm a'}} </span>\n        </p>\n      </div>\n    </div>\n  </div>\n  <!-- END NEWS WIDGET -->\n\n</div>\n"

/***/ }),
/* 380 */
/***/ (function(module, exports) {

module.exports = "\n<!-- START RESPONSIVE TABLES -->\n<div class=\"panel panel-default\">\n\n  <div class=\"panel-heading\">\n    <h3 class=\"panel-title\">Results</h3>\n    <ul class=\"panel-controls\">\n      <li><a (click)=\"refresResults()\" class=\"control-info\"><span class=\"glyphicon glyphicon-refresh\"></span></a></li>\n    </ul>\n  </div>\n\n  <div class=\"panel-body panel-body-table\">\n\n    <div class=\"table-responsive\">\n      <table class=\"table table-bordered table-striped table-actions\">\n        <thead>\n          <tr>\n            <th class=\"text-center\" width=\"200\">Test</th>\n            <th class=\"text-center\">Class</th>\n            <th class=\"text-center\" width=\"200\">Group</th>\n            <th class=\"text-center\" width=\"110\" *ngIf=\"isStudent()\">Score</th>\n            <th class=\"text-center\" width=\"110\" *ngIf=\"isTeacher()\">Average Score</th>\n            <th class=\"text-center\" width=\"110\">Actions</th>\n          </tr>\n        </thead>\n        <tbody>\n\n        <!-- Ter em atenção aqui inverter a ordem de inserção para mostrar os mais recentes primeiro-->\n        <tr *ngFor=\"let submission of submissionResults\">\n          <td class=\"text-center\"><strong>{{submission.exam.name}}</strong></td>\n          <td class=\"text-center\"><strong>{{submission.exam.group._class.name}}</strong></td>\n          <td class=\"text-center\"><strong>{{submission.exam.group.name}}</strong></td>\n          <td class=\"text-center\"><strong>{{submission.score}}</strong></td>\n          <td>\n            <button [routerLink]=\"['/dashboard','classes',submission.exam.group._class.id,'groups',submission.exam.group._class.id,'exams',submission.exam.id,'submission',submission.id]\" type=\"button\" class=\"btn btn-primary btn-rounded btn-sm\" *ngIf=\"isStudent()\">View Details</button>\n            <button [routerLink]=\"['/dashboard','classes',submission.exam.group._class.id,'groups',submission.exam.group._class.id,'exams',submission.exam.id,'results']\" type=\"button\" class=\"btn btn-primary btn-rounded btn-sm\" *ngIf=\"isTeacher()\">View Details</button>\n          </td>\n        </tr>\n        </tbody>\n      </table>\n    </div>\n\n  </div>\n</div>\n<!-- END RESPONSIVE TABLES -->\n"

/***/ }),
/* 381 */
/***/ (function(module, exports) {

module.exports = "<div class=\"row\">\n  <div class=\"col-md-12\" >\n    <angular2-fullcalendar [options]=\"calendarOptions\"></angular2-fullcalendar>\n  </div>\n</div>\n"

/***/ }),
/* 382 */
/***/ (function(module, exports) {

module.exports = "<!-- Navigation -->\n<nav id=\"mainNav\" class=\"navbar navbar-default navbar-fixed-top navbar-custom\">\n  <div class=\"container\">\n    <!-- Brand and toggle get grouped for better mobile display -->\n    <div class=\"navbar-header page-scroll\">\n      <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\n        <span class=\"sr-only\"></span><i class=\"fa fa-bars\"></i>\n      </button>\n      <a class=\"navbar-brand\" href=\"#page-top\">Evaluation System</a>\n    </div>\n\n\n    <!-- Collect the nav links, forms, and other content for toggling -->\n    <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n      <ul class=\"nav navbar-nav navbar-right\">\n        <li class=\"hidden\">\n          <a href=\"#page-top\"></a>\n        </li>\n        <li class=\"page-scroll\">\n          <a href=\"#portfolio\">Portfolio</a>\n        </li>\n        <li class=\"page-scroll\">\n          <a href=\"#about\">About</a>\n        </li>\n        <li class=\"page-scroll\">\n          <a href=\"#contact\">Contact</a>\n        </li>\n        <li class=\"page-scroll\">\n          <a [routerLink]=\"['/login']\">Sign In <span class=\"glyphicon glyphicon-log-in\"></span></a>\n        </li>\n        <li class=\"page-scroll\">\n          <a [routerLink]=\"['/register']\">Sign Up <span class=\"glyphicon glyphicon-log-in\"></span></a>\n        </li>\n      </ul>\n    </div>\n    <!-- /.navbar-collapse -->\n  </div>\n  <!-- /.container-fluid -->\n</nav>\n\n\n<!-- Header -->\n<header>\n  <div class=\"container\" id=\"maincontent\" tabindex=\"-1\">\n    <div class=\"row\">\n      <div class=\"col-lg-12\">\n        <img class=\"img-responsive\" alt=\"\">\n        <div class=\"intro-text\">\n          <h1 class=\"name\">Evaluation System</h1>\n          <hr class=\"star-light\">\n          <span class=\"skills\">Teachers - Students</span>\n        </div>\n      </div>\n    </div>\n  </div>\n</header>\n\n<!-- Portfolio Grid Section -->\n<section id=\"portfolio\">\n  <div class=\"container\">\n    <div class=\"row\">\n      <div class=\"col-lg-12 text-center\">\n        <h2>Portfolio</h2>\n        <hr class=\"star-primary\">\n      </div>\n    </div>\n    <div class=\"row\">\n      <div class=\"col-sm-4 portfolio-item\">\n        <a href=\"#portfolioModal1\" class=\"portfolio-link\" data-toggle=\"modal\">\n          <div class=\"caption\">\n            <div class=\"caption-content\">\n              <i class=\"fa fa-search-plus fa-3x\"></i>\n            </div>\n          </div>\n          <img class=\"img-responsive\" alt=\"Cabin\">\n        </a>\n      </div>\n      <div class=\"col-sm-4 portfolio-item\">\n        <a href=\"#portfolioModal2\" class=\"portfolio-link\" data-toggle=\"modal\">\n          <div class=\"caption\">\n            <div class=\"caption-content\">\n              <i class=\"fa fa-search-plus fa-3x\"></i>\n            </div>\n          </div>\n          <img class=\"img-responsive\" alt=\"Slice of cake\">\n        </a>\n      </div>\n      <div class=\"col-sm-4 portfolio-item\">\n        <a href=\"#portfolioModal3\" class=\"portfolio-link\" data-toggle=\"modal\">\n          <div class=\"caption\">\n            <div class=\"caption-content\">\n              <i class=\"fa fa-search-plus fa-3x\"></i>\n            </div>\n          </div>\n          <img class=\"img-responsive\" alt=\"Circus tent\">\n        </a>\n      </div>\n      <div class=\"col-sm-4 portfolio-item\">\n        <a href=\"#portfolioModal4\" class=\"portfolio-link\" data-toggle=\"modal\">\n          <div class=\"caption\">\n            <div class=\"caption-content\">\n              <i class=\"fa fa-search-plus fa-3x\"></i>\n            </div>\n          </div>\n          <img class=\"img-responsive\" alt=\"Game controller\">\n        </a>\n      </div>\n      <div class=\"col-sm-4 portfolio-item\">\n        <a href=\"#portfolioModal5\" class=\"portfolio-link\" data-toggle=\"modal\">\n          <div class=\"caption\">\n            <div class=\"caption-content\">\n              <i class=\"fa fa-search-plus fa-3x\"></i>\n            </div>\n          </div>\n          <img class=\"img-responsive\" alt=\"Safe\">\n        </a>\n      </div>\n      <div class=\"col-sm-4 portfolio-item\">\n        <a href=\"#portfolioModal6\" class=\"portfolio-link\" data-toggle=\"modal\">\n          <div class=\"caption\">\n            <div class=\"caption-content\">\n              <i class=\"fa fa-search-plus fa-3x\"></i>\n            </div>\n          </div>\n          <img class=\"img-responsive\" alt=\"Submarine\">\n        </a>\n      </div>\n    </div>\n  </div>\n</section>\n\n<!-- About Section -->\n<section class=\"success\" id=\"about\">\n  <div class=\"container\">\n    <div class=\"row\">\n      <div class=\"col-lg-12 text-center\">\n        <h2>About</h2>\n        <hr class=\"star-light\">\n      </div>\n    </div>\n    <div class=\"row\">\n      <div class=\"col-lg-4 col-lg-offset-2\">\n        <p>Evaluation system is a free app by help teacher and student to make exams</p>\n\n        <!--<p>Freelancer is a free bootstrap theme created by Start Bootstrap. The download includes the complete source files including HTML, CSS, and JavaScript as well as optional LESS stylesheets for easy customization.</p>-->\n      </div>\n      <div class=\"col-lg-4\">\n        <p>Whether you're a student looking to showcase your work, a professional looking to attract clients, or a graphic artist looking to share your projects, this template is the perfect starting point!</p>\n      </div>\n      <div class=\"col-lg-8 col-lg-offset-2 text-center\">\n        <a [routerLink]=\"['/register']\" class=\"btn btn-lg btn-outline\">\n          <span class=\"glyphicon glyphicon-log-in\"></span> Go To App\n        </a>\n      </div>\n    </div>\n  </div>\n</section>\n\n<!-- Contact Section -->\n<section id=\"contact\">\n  <div class=\"container\">\n    <div class=\"row\">\n      <div class=\"col-lg-12 text-center\">\n        <h2>Contact Me</h2>\n        <hr class=\"star-primary\">\n      </div>\n    </div>\n    <div class=\"row\">\n      <div class=\"col-lg-8 col-lg-offset-2\">\n        <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->\n        <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->\n        <form name=\"sentMessage\" id=\"contactForm\" novalidate>\n          <div class=\"row control-group\">\n            <div class=\"form-group col-xs-12 floating-label-form-group controls\">\n              <label for=\"name\">Name</label>\n              <input type=\"text\" class=\"form-control\" placeholder=\"Name\" id=\"name\" required data-validation-required-message=\"Please enter your name.\">\n              <p class=\"help-block text-danger\"></p>\n            </div>\n          </div>\n          <div class=\"row control-group\">\n            <div class=\"form-group col-xs-12 floating-label-form-group controls\">\n              <label for=\"email\">Email Address</label>\n              <input type=\"email\" class=\"form-control\" placeholder=\"Email Address\" id=\"email\" required data-validation-required-message=\"Please enter your email address.\">\n              <p class=\"help-block text-danger\"></p>\n            </div>\n          </div>\n          <div class=\"row control-group\">\n            <div class=\"form-group col-xs-12 floating-label-form-group controls\">\n              <label for=\"phone\">Phone Number</label>\n              <input type=\"tel\" class=\"form-control\" placeholder=\"Phone Number\" id=\"phone\" required data-validation-required-message=\"Please enter your phone number.\">\n              <p class=\"help-block text-danger\"></p>\n            </div>\n          </div>\n          <div class=\"row control-group\">\n            <div class=\"form-group col-xs-12 floating-label-form-group controls\">\n              <label for=\"message\">Message</label>\n              <textarea rows=\"5\" class=\"form-control\" placeholder=\"Message\" id=\"message\" required data-validation-required-message=\"Please enter a message.\"></textarea>\n              <p class=\"help-block text-danger\"></p>\n            </div>\n          </div>\n          <br>\n          <div id=\"success\"></div>\n          <div class=\"row\">\n            <div class=\"form-group col-xs-12\">\n              <button type=\"submit\" class=\"btn btn-success btn-lg\">Send</button>\n            </div>\n          </div>\n        </form>\n      </div>\n    </div>\n  </div>\n</section>\n"

/***/ }),
/* 383 */
/***/ (function(module, exports) {

module.exports = "<div class=\"login-container\">\n\n  <div class=\"login-box animated fadeInDown\">\n    <div class=\"login-body\">\n      <div class=\"login-title\"><strong>Welcome</strong>, Please login</div>\n      <div *ngIf=\"invalidEmail\" class=\"alert alert-danger\" role=\"alert\">\n        <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n        <strong>Invalid Email!</strong> Please use a valid email address.\n      </div>\n      <div *ngIf=\"emailOrPasswordWrong\" class=\"alert alert-danger\" role=\"alert\">\n        <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n        <strong>Wrong Email or Password!</strong>\n      </div>\n      <form class=\"form-horizontal\" (ngSubmit)=\"onSubmit()\" #f_login=\"ngForm\" novalidate>\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"text\" name=\"email\" class=\"form-control\" [(ngModel)]=\"user.email\" placeholder=\"Email\" #email=\"ngModel\" required/>\n            <div *ngIf=\"f_login.submitted && !email.valid\" class=\"help-block\">Email is required</div>\n            <!--<div *ngIf=\"\" class=\"alert alert-danger\">{{ }}</div>-->\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"password\" name=\"password\" class=\"form-control\" [(ngModel)]=\"user.password\" placeholder=\"Password\" #password=\"ngModel\" required/>\n            <div *ngIf=\"f_login.submitted && !password.valid\" class=\"help-block\">Password is required</div>\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-6\">\n            <button type=\"button\" [routerLink]=\"['/']\" class=\"btn btn-danger btn-block\">Cancel</button>\n          </div>\n          <div class=\"col-md-6\">\n            <button type=\"submit\" [disabled]=\"loading\" class=\"btn btn-info btn-block\">Log In</button>\n          </div>\n        </div>\n      </form>\n    </div>\n    <div class=\"login-footer\">\n      <div class=\"pull-left\">\n        &copy; 2017 Evaluation System\n      </div>\n      <div class=\"pull-right\">\n        <a href=\"#portefolio\">Portefolio</a> |\n        <a href=\"#about\">About</a> |\n        <a href=\"#contact\">Contact Us</a>\n      </div>\n    </div>\n  </div>\n\n</div>\n"

/***/ }),
/* 384 */
/***/ (function(module, exports) {

module.exports = "<br>\n\n<div class=\"row\">\n  <div class=\"col-md-12\">\n    <!-- START RESPONSIVE TABLES -->\n    <div class=\"panel panel-default\">\n\n      <div class=\"panel-heading\">\n        <h3 class=\"panel-title\">Notifications</h3>\n        <ul class=\"panel-controls\">\n          <li><a (click)=\"refreshNotifications()\" class=\"control-info\"><span class=\"glyphicon glyphicon-refresh\"></span></a></li>\n        </ul>\n      </div>\n\n      <div class=\"panel-body panel-body-table\">\n\n        <div class=\"table-responsive\">\n          <table class=\"table table-bordered table-striped table-actions\">\n            <thead>\n            <tr>\n              <th class=\"text-center\" width=\"200\">Class</th>\n              <th class=\"text-center\" width=\"200\">Group</th>\n              <th class=\"text-center\">Teacher</th>\n              <th class=\"text-center\" width=\"200\">Actions</th>\n            </tr>\n            </thead>\n            <tbody>\n\n            <!-- Ter em atenção aqui inverter a ordem de inserção para mostrar os mais recentes primeiro-->\n\n            <tr *ngFor=\"let notification of getNotificationsFilter()\">\n              <td class=\"text-center\"><strong>{{notification.group._class.name}}</strong></td>\n              <td class=\"text-center\"><strong>{{notification.group.name}}</strong></td>\n              <td class=\"text-center\"><strong>{{notification.group._class.teacher.firstName}} {{notification.group._class.teacher.lastName}}({{notification.group._class.teacher.email}})</strong></td>\n              <td>\n                <button (click)=\"this.declineNotification(notification.id)\" type=\"button\" class=\"btn btn-danger btn-rounded btn-sm\">Decline</button>\n                <button (click)=\"this.acceptNotification(notification.id)\" type=\"button\" class=\"btn btn-success btn-rounded btn-sm\">Accept</button>\n              </td>\n            </tr>\n\n            </tbody>\n          </table>\n        </div>\n\n      </div>\n    </div>\n    <!-- END RESPONSIVE TABLES -->\n\n  </div>\n</div>\n"

/***/ }),
/* 385 */
/***/ (function(module, exports) {

module.exports = "<br>\n<br>\n\n<div class=\"row\">\n  <div class=\"col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 \">\n    <div class=\"login-body\">\n\n      <div class=\"col-md-4 col-md-offset-4\">\n        <div class=\"profile-image profile-padding\">\n          <img src=\"../assets/images/users/no-image.jpg\"/>\n        </div>\n      </div>\n\n      <div *ngIf=\"updatedSuccess\" class=\"alert alert-success\" role=\"alert\">\n        <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n        <strong>Updated</strong> Updated with success\n      </div>\n\n      <div *ngIf=\"updatedError\" class=\"alert alert-success\" role=\"alert\">\n        <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n        <strong>Updated</strong> Updated with success\n      </div>\n\n      <div *ngIf=\"differentPassword\" class=\"alert alert-danger\" role=\"alert\">\n        <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n        <strong>Invalid Password!</strong> Please use a same Password.\n      </div>\n\n      <form (ngSubmit)=\"onSubmit()\" class=\"form-horizontal\" method=\"post\" #f_register=\"ngForm\">\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"text\" name=\"firstName\" class=\"form-control\" [(ngModel)]=\"user.firstName\"  #firstName=\"ngModel\" required/>\n            <div *ngIf=\"f_register.submitted && !firstName.valid\" class=\"help-block\">First Name is required</div>\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"text\" name=\"lastName\" class=\"form-control\" [(ngModel)]=\"user.lastName\" #lastName=\"ngModel\" required/>\n            <div *ngIf=\"f_register.submitted && !lastName.valid\" class=\"help-block\">Last Name is required</div>\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"text\" name=\"email\" class=\"form-control\" [(ngModel)]=\"user.email\" #email=\"ngModel\" disabled=\"true\"/>\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"password\" name=\"password\" class=\"form-control\" [(ngModel)]=\"user.password\" placeholder=\"New Password\" #password=\"ngModel\" required/>\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"password\" name=\"confirmPassword\" class=\"form-control\" [(ngModel)]=\"user.confirmPassword\" placeholder=\"Confirm New Password\" #confirmPassword=\"ngModel\"/>\n            <div *ngIf=\"f_register.submitted && this.user.password && !confirmPassword.valid\" class=\"help-block\">Confirm New Password is required</div>\n          </div>\n        </div>\n        <div class=\"form-group typeUser\">\n          <label class=\"col-lg-3 col-md-3 col-sm-3 col-xs-4\">Type User:</label>\n          <div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-4\" id=\"ProductOwner\">\n            <input type=\"radio\" value=\"Student\" name=\"type\" [(ngModel)]=\"user.type\" disabled=\"true\"> Student\n          </div>\n          <div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-4\" id=\"ScrumMaster\" >\n            <input type=\"radio\" value=\"Teacher\" name=\"type\" [(ngModel)]=\"user.type\" disabled=\"true\"> Teacher\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-6 col-md-offset-6\">\n            <button type=\"submit\" [disabled]=\"loading\" class=\"btn btn-info btn-block\">Save</button>\n          </div>\n        </div>\n      </form>\n    </div>\n  </div>\n</div>\n"

/***/ }),
/* 386 */
/***/ (function(module, exports) {

module.exports = "<div class=\"login-container\">\n\n  <div class=\"login-box animated fadeInDown\">\n    <div class=\"login-body\">\n      <div class=\"login-title\"><strong>Welcome</strong>, Please Register</div>\n      <div *ngIf=\"invalidEmail\" class=\"alert alert-danger\" role=\"alert\">\n        <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n        <strong>Invalid Email!</strong> Please use a valid email address.\n      </div>\n      <div *ngIf=\"differentPassword\" class=\"alert alert-danger\" role=\"alert\">\n        <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n        <strong>Invalid Password!</strong> Please use a same Password.\n      </div>\n      <div *ngIf=\"emailAlreadyExists\" class=\"alert alert-danger\" role=\"alert\">\n        <button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n        <strong>Email already Exists!</strong> Please choose another Email.\n      </div>\n      <form (ngSubmit)=\"onSubmit()\" class=\"form-horizontal\" method=\"post\" #f_register=\"ngForm\">\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"text\" name=\"firstName\" class=\"form-control\" [(ngModel)]=\"user.firstName\" placeholder=\"First Name\"  #firstName=\"ngModel\" required/>\n            <div *ngIf=\"f_register.submitted && !firstName.valid\" class=\"help-block\">First Name is required</div>\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"text\" name=\"lastName\" class=\"form-control\" [(ngModel)]=\"user.lastName\" placeholder=\"Last Name\" #lastName=\"ngModel\" required/>\n            <div *ngIf=\"f_register.submitted && !lastName.valid\" class=\"help-block\">Last Name is required</div>\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"text\" name=\"email\" class=\"form-control\" [(ngModel)]=\"user.email\" placeholder=\"Email\" #email=\"ngModel\" required/>\n            <div *ngIf=\"f_register.submitted && !email.valid\" class=\"help-block\">Email is required</div>\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"password\" name=\"password\" class=\"form-control\" [(ngModel)]=\"user.password\" placeholder=\"Password\" #password=\"ngModel\" required/>\n            <div *ngIf=\"f_register.submitted && !password.valid\" class=\"help-block\">Password is required</div>\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-12\">\n            <input type=\"password\" name=\"confirmPassword\" class=\"form-control\" [(ngModel)]=\"user.confirmPassword\" placeholder=\"Confirm Password\" #confirmPassword=\"ngModel\" required/>\n            <div *ngIf=\"f_register.submitted && !confirmPassword.valid\" class=\"help-block\">Confirm Password is required</div>\n          </div>\n        </div>\n        <div class=\"form-group typeUser\">\n          <label class=\"col-lg-3 col-md-3 col-sm-3 col-xs-4\">Type User:</label>\n          <div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-4\" id=\"ProductOwner\">\n            <input type=\"radio\" value=\"Student\" name=\"type\" [(ngModel)]=\"user.type\"> Student\n          </div>\n          <div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-4\" id=\"ScrumMaster\" >\n            <input type=\"radio\" value=\"Teacher\" name=\"type\" [(ngModel)]=\"user.type\"> Teacher\n          </div>\n        </div>\n        <div class=\"form-group\">\n          <div class=\"col-md-6\">\n            <button type=\"button\" [routerLink]=\"['/']\" class=\"btn btn-danger btn-block\">Cancel</button>\n          </div>\n          <div class=\"col-md-6\">\n            <button type=\"submit\" [disabled]=\"loading\" class=\"btn btn-info btn-block\">Register</button>\n          </div>\n        </div>\n      </form>\n    </div>\n    <div class=\"login-footer\">\n      <div class=\"pull-left\">\n        &copy; 2017 Evaluation System\n      </div>\n      <div class=\"pull-right\">\n        <a href=\"#portefolio\">Portefolio</a> |\n        <a href=\"#about\">About</a> |\n        <a href=\"#contact\">Contact Us</a>\n      </div>\n    </div>\n  </div>\n\n</div>\n"

/***/ }),
/* 387 */,
/* 388 */,
/* 389 */,
/* 390 */,
/* 391 */,
/* 392 */,
/* 393 */,
/* 394 */,
/* 395 */,
/* 396 */,
/* 397 */,
/* 398 */,
/* 399 */,
/* 400 */,
/* 401 */,
/* 402 */,
/* 403 */,
/* 404 */,
/* 405 */,
/* 406 */,
/* 407 */,
/* 408 */,
/* 409 */,
/* 410 */,
/* 411 */,
/* 412 */,
/* 413 */,
/* 414 */,
/* 415 */,
/* 416 */,
/* 417 */,
/* 418 */,
/* 419 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(248);


/***/ })
],[419]);
//# sourceMappingURL=main.bundle.js.map