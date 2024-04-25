"use strict";(self.webpackChunkfitness=self.webpackChunkfitness||[]).push([[464],{1464:(D,p,r)=>{r.r(p),r.d(p,{UserModule:()=>z});var d=r(6895),u=r(433),m=r(5009),C=r(5808),c=r(805),t=r(8256);let f=(()=>{class n{static#t=this.\u0275fac=function(o){return new(o||n)};static#e=this.\u0275cmp=t.Xpm({type:n,selectors:[["app-create-user"]],decls:2,vars:0,template:function(o,i){1&o&&(t.TgZ(0,"p"),t._uU(1,"create-user works!"),t.qZA())}})}return n})();var h=r(5955),_=r(3146),M=r(1584),b=r(5849),g=r(3009),x=r(5593),P=r(1740),O=r(2137),v=r(4044);function y(n,l){if(1&n){const e=t.EpF();t.TgZ(0,"div",7)(1,"span")(2,"input",8),t.NdJ("input",function(i){t.CHM(e);const a=t.oxw(),s=t.MAs(2);return t.KtG(s.filterGlobal(a.applyFilterGlobal(i),"contains"))}),t.qZA()(),t.TgZ(3,"button",9),t.NdJ("click",function(){t.CHM(e);const i=t.oxw(),a=t.MAs(2);return t.KtG(i.clear(a))}),t.qZA()()}}function U(n,l){1&n&&(t.TgZ(0,"tr")(1,"th",10)(2,"div",11),t._uU(3," Contact "),t._UZ(4,"p-columnFilter",12),t.qZA()(),t.TgZ(5,"th",10)(6,"div",11),t._uU(7," Role "),t._UZ(8,"p-columnFilter",13),t.qZA()(),t.TgZ(9,"th",10)(10,"div",11),t._uU(11," Email "),t._UZ(12,"p-columnFilter",14),t.qZA()()())}function T(n,l){if(1&n){const e=t.EpF();t.TgZ(0,"tr")(1,"td"),t._uU(2),t.qZA(),t.TgZ(3,"td"),t._uU(4),t.qZA(),t.TgZ(5,"td"),t._uU(6),t.qZA(),t.TgZ(7,"td")(8,"button",15),t.NdJ("click",function(){const a=t.CHM(e).$implicit,s=t.oxw();return t.KtG(s.updateUser(a))}),t.qZA()(),t.TgZ(9,"td")(10,"button",16),t.NdJ("click",function(){const a=t.CHM(e).$implicit,s=t.oxw();return t.KtG(s.confirmDelete(a.id))}),t.qZA()()()}if(2&n){const e=l.$implicit;t.xp6(2),t.AsE(" ",e.contact.nom," ",e.contact.prenom," "),t.xp6(2),t.hij(" ",e.role.rolename," "),t.xp6(2),t.hij(" ",e.email," ")}}function Z(n,l){1&n&&(t.TgZ(0,"tr")(1,"td",17),t._uU(2,"Aucun utilisateur trouv\xe9."),t.qZA()())}const w=function(){return[5,10,20]},S=function(){return["role.rolename","contact.nom","contact.prenom"]},A=function(){return{"min-width":"80 rem"}},F=function(){return{width:"50vw"}},Y=[{path:"",component:(()=>{class n{constructor(e,o,i,a){this.userService=e,this.dialogService=o,this.messageService=i,this.confirmationService=a,this.loading=!0}ngOnInit(){this.usersSubscription=this.userService.getUsers().subscribe({next:e=>{console.log(e),this.users=e},error:e=>{console.log(e)},complete:()=>{console.log("task complete")}}),this.loading=!1}ngOnDestroy(){this.deleteUserSubscription&&this.deleteUserSubscription.unsubscribe(),this.usersSubscription&&this.usersSubscription.unsubscribe()}clear(e){e.clear()}applyFilterGlobal(e){return e.target.value}show(){this.ref=this.dialogService.open(f,{header:"Create User",width:"70vw",height:"50vw",modal:!0,contentStyle:{overflow:"auto"},maximizable:!0})}updateUser(e){this.ref=this.dialogService.open(h.j,{data:{user:e},header:" Update User"})}deleteUser(e){this.deleteUserSubscription=this.userService.deleteUser(e).subscribe({next:o=>{console.log(o)},error:o=>{console.log(o)},complete:()=>{console.log("task complete")}})}confirmDelete(e){this.confirmationService.confirm({message:"Do you want to delete this record?",header:"Delete Confirmation",icon:"pi pi-info-circle",accept:()=>{this.deleteUser(e),this.messageService.add({severity:"info",summary:"Confirmed",detail:"Record deleted"})},reject:o=>{switch(o){case c.wB.REJECT:this.messageService.add({severity:"error",summary:"Rejected",detail:"You have rejected"});break;case c.wB.CANCEL:this.messageService.add({severity:"warn",summary:"Cancelled",detail:"You have cancelled"})}}})}displayContactCard(e){this.ref=this.dialogService.open(_._,{data:{contact:e},header:" Card",width:"70vw",height:"50vw",modal:!0,contentStyle:{overflow:"auto"},maximizable:!0})}static#t=this.\u0275fac=function(o){return new(o||n)(t.Y36(M.K),t.Y36(b.xA),t.Y36(c.ez),t.Y36(c.YP))};static#e=this.\u0275cmp=t.Xpm({type:n,selectors:[["app-user"]],decls:9,vars:15,consts:[[1,"card"],["dataKey","id","currentPageReportTemplate","Affichage du {first} au {last} des {totalRecords} entr\xe9es","columnResizeMode","expand","styleClass","p-datatable-gridlines",3,"value","rows","showCurrentPageReport","rowsPerPageOptions","loading","paginator","globalFilterFields","resizableColumns","tableStyle"],["dt1",""],["pTemplate","caption"],["pTemplate","header"],["pTemplate","body"],["pTemplate","emptymessage"],[1,"flex"],["pInputText","","type","text","placeholder","Search keyword",3,"input"],["pButton","","label","Clear","icon","pi pi-filter-slash",1,"p-button-outlined",3,"click"],[2,"min-width","20rem"],[1,"flex","align-items-center"],["type","text","field","contact","display","menu"],["type","text","field","role","display","menu"],["type","text","field","email","display","menu"],["pButton","","type","button","icon","pi pi-pencil",3,"click"],["pButton","","type","button","icon","pi pi-trash",3,"click"],["colspan","7"]],template:function(o,i){1&o&&(t.TgZ(0,"div",0)(1,"p-table",1,2),t.YNc(3,y,4,0,"ng-template",3),t.YNc(4,U,13,0,"ng-template",4),t.YNc(5,T,11,4,"ng-template",5),t.YNc(6,Z,3,0,"ng-template",6),t.qZA()(),t._UZ(7,"p-toast")(8,"p-confirmDialog")),2&o&&(t.xp6(1),t.Q6J("value",i.users)("rows",10)("showCurrentPageReport",!0)("rowsPerPageOptions",t.DdM(11,w))("loading",i.loading)("paginator",!0)("globalFilterFields",t.DdM(12,S))("resizableColumns",!0)("tableStyle",t.DdM(13,A)),t.xp6(7),t.Akn(t.DdM(14,F)))},dependencies:[g.iA,c.jx,g.xl,x.Hq,P.o,O.Q,v.FN],styles:[".flex[_ngcontent-%COMP%]{display:flex;align-items:center}.flex[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]{margin-right:10px}.flex[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]{padding:12px;font-size:16px;border:1px solid #ddd;border-radius:5px;width:250px;box-shadow:0 0 5px #0000001a}.flex[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]{margin-left:10px}.card[_ngcontent-%COMP%]{margin:20px;padding:20px;border:1px solid #ddd;border-radius:8px;box-shadow:0 0 10px #0000001a;background-color:#fff}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]{width:100%;margin-top:20px}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   .p-datatable-header[_ngcontent-%COMP%]{background-color:#f2f2f2;border-bottom:1px solid #ddd}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   .p-datatable-header[_ngcontent-%COMP%]   .p-column-title[_ngcontent-%COMP%]{font-weight:700}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   .p-datatable-header[_ngcontent-%COMP%]   .p-paginator[_ngcontent-%COMP%]{margin-top:10px}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   .p-datatable-caption[_ngcontent-%COMP%]{display:flex;justify-content:space-between;align-items:center}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   .p-datatable-caption[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]{margin-right:10px}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   .p-datatable-caption[_ngcontent-%COMP%]   .p-input-icon-left[_ngcontent-%COMP%]{position:relative}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   .p-datatable-caption[_ngcontent-%COMP%]   .p-input-icon-left[_ngcontent-%COMP%]   i[_ngcontent-%COMP%]{position:absolute;right:10px;top:50%;transform:translateY(-50%)}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   .p-datatable-caption[_ngcontent-%COMP%]   .p-input-icon-left[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]{padding-right:30px}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   th[_ngcontent-%COMP%], .card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   td[_ngcontent-%COMP%]{padding:10px;text-align:left;border:1px solid #ccc}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   th[_ngcontent-%COMP%]{background-color:#f2f2f2}.card[_ngcontent-%COMP%]   .p-table[_ngcontent-%COMP%]   tr[_ngcontent-%COMP%]:hover{background-color:#f9f9f9}"]})}return n})()}];let j=(()=>{class n{static#t=this.\u0275fac=function(o){return new(o||n)};static#e=this.\u0275mod=t.oAB({type:n});static#n=this.\u0275inj=t.cJS({imports:[d.ez,C.Bz.forChild(Y)]})}return n})(),z=(()=>{class n{static#t=this.\u0275fac=function(o){return new(o||n)};static#e=this.\u0275mod=t.oAB({type:n});static#n=this.\u0275inj=t.cJS({imports:[d.ez,u.u5,m.g,j]})}return n})()}}]);