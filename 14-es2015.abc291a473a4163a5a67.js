(window.webpackJsonp=window.webpackJsonp||[]).push([[14],{L6id:function(l,n,u){"use strict";u.r(n);var t=u("8Y7J");class e{}var o=u("pMnS"),r=u("oBZk"),a=u("ZZ/e"),b=u("SVse"),i=u("Xr7G");let c=(()=>{class l{constructor(l){this.firestore=l}read_Students(){return this.firestore.collection("users").snapshotChanges()}}return l.ngInjectableDef=t.Lb({factory:function(){return new l(t.Mb(i.a))},token:l,providedIn:"root"}),l})();class s{constructor(l,n){this.crudService=l,this.menu=n,this.menu.enable(!0)}ngOnInit(){this.crudService.read_Students().subscribe(l=>{this.students=l.map(l=>({id:l.payload.doc.id,isEdit:!1,Name:l.payload.doc.data().Name,Age:l.payload.doc.data().Age,Address:l.payload.doc.data().Address})),console.log(this.students)})}}var d=t.pb({encapsulation:0,styles:[[".welcome-card[_ngcontent-%COMP%]   img[_ngcontent-%COMP%]{max-height:35vh;overflow:hidden}"]],data:{}});function h(l){return t.Ib(0,[(l()(),t.rb(0,0,null,null,6,"ion-card",[],null,null,null,r.z,r.d)),t.qb(1,49152,null,0,a.j,[t.h,t.k,t.x],null,null),(l()(),t.rb(2,0,null,0,4,"ion-card-header",[],null,null,null,r.x,r.e)),t.qb(3,49152,null,0,a.l,[t.h,t.k,t.x],null,null),(l()(),t.rb(4,0,null,0,2,"ion-card-title",[],null,null,null,r.y,r.f)),t.qb(5,49152,null,0,a.n,[t.h,t.k,t.x],null,null),(l()(),t.Hb(6,0,["",""]))],null,function(l,n){l(n,6,0,n.context.$implicit.id)})}function p(l){return t.Ib(0,[(l()(),t.rb(0,0,null,null,6,"ion-header",[],null,null,null,r.B,r.h)),t.qb(1,49152,null,0,a.y,[t.h,t.k,t.x],null,null),(l()(),t.rb(2,0,null,0,4,"ion-toolbar",[],null,null,null,r.N,r.t)),t.qb(3,49152,null,0,a.yb,[t.h,t.k,t.x],null,null),(l()(),t.rb(4,0,null,0,2,"ion-title",[],null,null,null,r.M,r.s)),t.qb(5,49152,null,0,a.wb,[t.h,t.k,t.x],null,null),(l()(),t.Hb(-1,0,[" Ionic 4 Firestore CRUD Operation Demo "])),(l()(),t.rb(7,0,null,null,3,"ion-content",[["padding",""]],null,null,null,r.A,r.g)),t.qb(8,49152,null,0,a.r,[t.h,t.k,t.x],null,null),(l()(),t.gb(16777216,null,0,1,null,h)),t.qb(10,278528,null,0,b.h,[t.M,t.J,t.q],{ngForOf:[0,"ngForOf"]},null)],function(l,n){l(n,10,0,n.component.students)},null)}function A(l){return t.Ib(0,[(l()(),t.rb(0,0,null,null,1,"app-home",[],null,null,null,p,d)),t.qb(1,114688,null,0,s,[c,a.Cb],null,null)],function(l,n){l(n,1,0)},null)}var f=t.nb("app-home",s,A,{},{},[]),g=u("s7LF"),m=u("iInd");u.d(n,"HomePageModuleNgFactory",function(){return k});var k=t.ob(e,[],function(l){return t.zb([t.Ab(512,t.j,t.Z,[[8,[o.a,f]],[3,t.j],t.v]),t.Ab(4608,b.j,b.i,[t.s,[2,b.p]]),t.Ab(4608,g.d,g.d,[]),t.Ab(4608,a.a,a.a,[t.x,t.g]),t.Ab(4608,a.Db,a.Db,[a.a,t.j,t.p]),t.Ab(4608,a.Gb,a.Gb,[a.a,t.j,t.p]),t.Ab(1073742336,b.b,b.b,[]),t.Ab(1073742336,g.c,g.c,[]),t.Ab(1073742336,g.a,g.a,[]),t.Ab(1073742336,a.Ab,a.Ab,[]),t.Ab(1073742336,m.o,m.o,[[2,m.t],[2,m.m]]),t.Ab(1073742336,e,e,[]),t.Ab(1024,m.k,function(){return[[{path:"",component:s}]]},[])])})}}]);