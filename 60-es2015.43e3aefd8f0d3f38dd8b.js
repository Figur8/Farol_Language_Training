(window.webpackJsonp=window.webpackJsonp||[]).push([[60],{Gdks:function(e,o,t){"use strict";t.r(o),t.d(o,"ion_popover",function(){return b});var i=t("S6Yj"),r=(t("OoTa"),t("oM6z")),s=(t("igpV"),t("Xq1y")),n=t("pyhm"),p=t("tkfp"),a=t("ckTY");const l=(e,o)=>{let t="top",i="left";const s=e.querySelector(".popover-content"),n=s.getBoundingClientRect(),p=n.width,a=n.height,l=e.ownerDocument.defaultView.innerWidth,d=e.ownerDocument.defaultView.innerHeight,h=o&&o.target&&o.target.getBoundingClientRect(),v=null!=h&&"top"in h?h.top:d/2-a/2,b=null!=h&&"left"in h?h.left:l/2,m=h&&h.width||0,f=h&&h.height||0,u=e.querySelector(".popover-arrow"),w=u.getBoundingClientRect(),g=w.width,x=w.height;null==h&&(u.style.display="none");const y={top:v+f,left:b+m/2-g/2},j={top:v+f+(x-1),left:b+m/2-p/2};let O=!1,k=!1;j.left<c+25?(O=!0,j.left=c):p+c+j.left+25>l&&(k=!0,j.left=l-p-c,i="right"),v+f+a>d&&v-a>0?(y.top=v-(x+1),j.top=v-a-(x-1),e.className=e.className+" popover-bottom",t="bottom"):v+f+a>d&&(s.style.bottom=c+"%"),u.style.top=y.top+"px",u.style.left=y.left+"px",s.style.top=j.top+"px",s.style.left=j.left+"px",O&&(s.style.left=`calc(${j.left}px + var(--ion-safe-area-left, 0px))`),k&&(s.style.left=`calc(${j.left}px - var(--ion-safe-area-right, 0px))`),s.style.transformOrigin=t+" "+i;const D=Object(r.a)(),E=Object(r.a)(),P=Object(r.a)();return E.addElement(e.querySelector("ion-backdrop")).fromTo("opacity",.01,.08),P.addElement(e.querySelector(".popover-wrapper")).fromTo("opacity",.01,1),D.addElement(e).easing("ease").duration(100).addAnimation([E,P])},c=5,d=e=>{const o=Object(r.a)(),t=Object(r.a)(),i=Object(r.a)();return t.addElement(e.querySelector("ion-backdrop")).fromTo("opacity",.08,0),i.addElement(e.querySelector(".popover-wrapper")).fromTo("opacity",.99,0),o.addElement(e).easing("ease").duration(500).addAnimation([t,i])},h=(e,o)=>{const t=e.ownerDocument,i="rtl"===t.dir;let s="top",n=i?"right":"left";const p=e.querySelector(".popover-content"),a=p.getBoundingClientRect(),l=a.width,c=a.height,d=t.defaultView.innerWidth,h=t.defaultView.innerHeight,v=o&&o.target&&o.target.getBoundingClientRect(),b=null!=v&&"bottom"in v?v.bottom:h/2-c/2,m=v&&v.height||0,f={top:b,left:null!=v&&"left"in v?i?v.left-l+v.width:v.left:d/2-l/2};f.left<12?(f.left=12,n="left"):l+12+f.left>d&&(f.left=d-l-12,n="right"),b+m+c>h&&b-c>0?(f.top=b-c-m,e.className=e.className+" popover-bottom",s="bottom"):b+m+c>h&&(p.style.bottom="12px");const u=Object(r.a)(),w=Object(r.a)(),g=Object(r.a)(),x=Object(r.a)(),y=Object(r.a)();return w.addElement(e.querySelector("ion-backdrop")).fromTo("opacity",.01,.32),g.addElement(e.querySelector(".popover-wrapper")).fromTo("opacity",.01,1),x.addElement(p).beforeStyles({top:`${f.top}px`,left:`${f.left}px`,"transform-origin":`${s} ${n}`}).fromTo("transform","scale(0.001)","scale(1)"),y.addElement(e.querySelector(".popover-viewport")).fromTo("opacity",.01,1),u.addElement(e).easing("cubic-bezier(0.36,0.66,0.04,1)").duration(300).addAnimation([w,g,x,y])},v=e=>{const o=Object(r.a)(),t=Object(r.a)(),i=Object(r.a)();return t.addElement(e.querySelector("ion-backdrop")).fromTo("opacity",.32,0),i.addElement(e.querySelector(".popover-wrapper")).fromTo("opacity",.99,0),o.addElement(e).easing("ease").duration(500).addAnimation([t,i])},b=class{constructor(e){Object(i.m)(this,e),this.presented=!1,this.mode=Object(i.e)(this),this.keyboardClose=!0,this.backdropDismiss=!0,this.showBackdrop=!0,this.translucent=!1,this.animated=!0,this.onDismiss=e=>{e.stopPropagation(),e.preventDefault(),this.dismiss()},this.onBackdropTap=()=>{this.dismiss(void 0,s.a)},this.onLifecycle=e=>{const o=this.usersElement,t=m[e.type];if(o&&t){const i=new CustomEvent(t,{bubbles:!1,cancelable:!1,detail:e.detail});o.dispatchEvent(i)}},this.didPresent=Object(i.d)(this,"ionPopoverDidPresent",7),this.willPresent=Object(i.d)(this,"ionPopoverWillPresent",7),this.willDismiss=Object(i.d)(this,"ionPopoverWillDismiss",7),this.didDismiss=Object(i.d)(this,"ionPopoverDidDismiss",7)}async present(){if(this.presented)return;const e=this.el.querySelector(".popover-content");if(!e)throw new Error("container is undefined");const o=Object.assign({},this.componentProps,{popover:this.el});return this.usersElement=await Object(p.a)(this.delegate,e,this.component,["popover-viewport",this.el["s-sc"]],o),await Object(a.a)(this.usersElement),Object(s.e)(this,"popoverEnter",l,h,this.event)}async dismiss(e,o){const t=await Object(s.f)(this,e,o,"popoverLeave",d,v,this.event);return t&&await Object(p.b)(this.delegate,this.usersElement),t}onDidDismiss(){return Object(s.g)(this.el,"ionPopoverDidDismiss")}onWillDismiss(){return Object(s.g)(this.el,"ionPopoverWillDismiss")}render(){const e=Object(i.e)(this),{onLifecycle:o}=this;return Object(i.i)(i.a,{"aria-modal":"true","no-router":!0,style:{zIndex:`${2e4+this.overlayIndex}`},class:Object.assign({},Object(n.b)(this.cssClass),{[e]:!0,"popover-translucent":this.translucent}),onIonPopoverDidPresent:o,onIonPopoverWillPresent:o,onIonPopoverWillDismiss:o,onIonPopoverDidDismiss:o,onIonDismiss:this.onDismiss,onIonBackdropTap:this.onBackdropTap},Object(i.i)("ion-backdrop",{tappable:this.backdropDismiss,visible:this.showBackdrop}),Object(i.i)("div",{class:"popover-wrapper"},Object(i.i)("div",{class:"popover-arrow"}),Object(i.i)("div",{class:"popover-content"})))}get el(){return Object(i.f)(this)}static get style(){return'.sc-ion-popover-ios-h{--background:var(--ion-background-color,#fff);--min-width:0;--min-height:0;--max-width:auto;--height:auto;left:0;right:0;top:0;bottom:0;display:-ms-flexbox;display:flex;position:fixed;-ms-flex-align:center;align-items:center;-ms-flex-pack:center;justify-content:center;color:var(--ion-text-color,#000);z-index:1000}.overlay-hidden.sc-ion-popover-ios-h{display:none}.popover-wrapper.sc-ion-popover-ios{opacity:0;z-index:10}.popover-content.sc-ion-popover-ios{display:-ms-flexbox;display:flex;position:absolute;-ms-flex-direction:column;flex-direction:column;width:var(--width);min-width:var(--min-width);max-width:var(--max-width);height:var(--height);min-height:var(--min-height);max-height:var(--max-height);background:var(--background);-webkit-box-shadow:var(--box-shadow);box-shadow:var(--box-shadow);overflow:auto;z-index:10}.popover-viewport.sc-ion-popover-ios{--ion-safe-area-top:0px;--ion-safe-area-right:0px;--ion-safe-area-bottom:0px;--ion-safe-area-left:0px}.sc-ion-popover-ios-h{--width:200px;--max-height:90%;--box-shadow:none}.popover-content.sc-ion-popover-ios{border-radius:10px}.popover-arrow.sc-ion-popover-ios{display:block;position:absolute;width:20px;height:10px;overflow:hidden}.popover-arrow.sc-ion-popover-ios:after{left:3px;top:3px;border-radius:3px;position:absolute;width:14px;height:14px;-webkit-transform:rotate(45deg);transform:rotate(45deg);background:var(--background);content:"";z-index:10}[dir=rtl].sc-ion-popover-ios-h .popover-arrow.sc-ion-popover-ios:after, [dir=rtl] .sc-ion-popover-ios-h .popover-arrow.sc-ion-popover-ios:after, [dir=rtl].sc-ion-popover-ios .popover-arrow.sc-ion-popover-ios:after{left:unset;right:unset;right:3px}.popover-bottom.sc-ion-popover-ios-h .popover-arrow.sc-ion-popover-ios{top:auto;bottom:-10px}.popover-bottom.sc-ion-popover-ios-h .popover-arrow.sc-ion-popover-ios:after{top:-6px}@supports ((-webkit-backdrop-filter:blur(0)) or (backdrop-filter:blur(0))){.popover-translucent.sc-ion-popover-ios-h .popover-arrow.sc-ion-popover-ios:after, .popover-translucent.sc-ion-popover-ios-h .popover-content.sc-ion-popover-ios{background:rgba(var(--ion-background-color-rgb,255,255,255),.8);-webkit-backdrop-filter:saturate(180%) blur(20px);backdrop-filter:saturate(180%) blur(20px)}}'}},m={ionPopoverDidPresent:"ionViewDidEnter",ionPopoverWillPresent:"ionViewWillEnter",ionPopoverWillDismiss:"ionViewWillLeave",ionPopoverDidDismiss:"ionViewDidLeave"}}}]);