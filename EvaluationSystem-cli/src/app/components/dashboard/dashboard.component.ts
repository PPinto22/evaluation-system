import {AfterViewInit, Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {BreadCrumbService} from '../../services/breadcrumb.service';
import {Router} from '@angular/router';
import {GroupService} from '../../services/group.service';
import {ClassesService} from "../../services/classes.service";

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, AfterViewInit, OnChanges {

  private nameInToggleNavigation;
  private page_navigation_toggled: boolean;
  private collapse_struture: any;

  constructor(
    private router: Router,
    private authentication: AuthenticationService,
    private breadCrumb: BreadCrumbService,
    private groupsService: GroupService,
    private classesService: ClassesService
  ) {
  }

  ngOnInit() {
    this.setNamebreadCrum();
    this.createNavbarStructure();
    this.page_navigation_toggled = false;
    this.getClasses();
  }



  private getClasses(): void {
    this.classesService.getAllClassesByUser( this.authentication.getUserId() ).subscribe(
      result => {
        console.log(result);
        const classes_dash = this.collapse_struture[3];
        for (const resul_class of result ){
          const class_dash = classes_dash.children.find( obj => resul_class.id === obj.class_id );
          if (class_dash) { // já existe a class criada
            this.getGroups( class_dash, resul_class.id);
          }else { // não existe a class criada
            classes_dash.children.push( {
              class_id: resul_class.id,
              name: resul_class.abbreviation,
              route: ['/dashboard', 'classes', '' + resul_class.id],
              isCollapsed: false,
              children: []
            });

            this.getGroups( classes_dash.children.find( obj => resul_class.id === obj.class_id ), resul_class.id );

          }
        }
        console.log(this.collapse_struture);
      },
      error => {
        console.log('error get classes by user');
      }
    );
  }

  private getGroups(class_dash: any, class_id: number): void {
    this.groupsService.getGroupByClasse(class_id).subscribe(
      result => {
        for (const group of result) {
          const group_dash = class_dash.children.find(obj => class_id === obj.group_id);
          if (!group_dash) { // não existe o grupo
            class_dash.children.push({
              group_id: group.id,
              name: group.name,
              route: ['/dashboard', 'classes', '' + group._class.id, 'groups', '' + group.id],
              isCollapsed: false
            });
          }
        }
      },
      error => {
        console.log('error get groups by user');
      });
  }



  public setNamebreadCrum() {
    this.breadCrumb.breadCrumDate.subscribe( value => {
        this.nameInToggleNavigation = value.pop();
      }
    );
  }

  ngAfterViewInit() {
    x_navigation();
    page_content_onresize();
  }

  ngOnChanges() {
    x_navigation();
  }

  private createNavbarStructure(): void {
    this.collapse_struture = [
      { name: 'Dashboard', route: ['/dashboard'], isCollapsed: false },
      { name: 'Schedule', route: ['/dashboard', 'schedule'], isCollapsed: false },
      { name: 'Results', route: ['/dashboard', 'results'], isCollapsed: false },
      { name: 'Classes', route: [], isCollapsed: false , children: []}
    ];
  }

  public navigateRoute(route: string[], collapse_node: any, collapse_parent: any) {

    for ( const node of collapse_parent ) {
      node.isCollapsed = false;
    }

    for ( const node of collapse_node ) {
      node.isCollapsed = false;
    }

    collapse_node.isCollapsed = true;

    if (route.length > 0 ) {
      this.router.navigate(route);
    }

    console.log(this.collapse_struture);


  }

  public clearActive(collapse_node: any, collapse_parent: any) {
    for ( const node of collapse_parent ){
      if ( collapse_node !== node) {
        node.isCollapsed = false;
      }
    }
  }

  public toggledPageNavigation(): void {
    this.page_navigation_toggled = !this.page_navigation_toggled;
  }

  public getUserName(): string {
    return this.authentication.getUserName();
  }

  public getUserType(): string {
    return this.authentication.userLogged.type;
  }

  public logout() {
    this.authentication.logout();
    this.router.navigate(['/']);
  }

}
