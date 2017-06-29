import {AfterViewInit, Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {BreadCrumbService} from '../../services/breadcrumb.service';
import {Router} from '@angular/router';
import {GroupService} from '../../services/group.service';
import {ClassesService} from '../../services/classes.service';

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
          const class_dash = classes_dash.children.find( obj => resul_class.id === obj.id );
          if (class_dash) { // já existe a class criada
            this.getGroups( class_dash, resul_class.id);
          }else { // não existe a class criada
            classes_dash.children.push( {
              level: 2,
              id: resul_class.id,
              name: resul_class.abbreviation,
              route: ['/dashboard', 'classes', '' + resul_class.id],
              isCollapsed: false,
              children: []
            });

            this.getGroups( classes_dash.children.find( obj => resul_class.id === obj.id ), resul_class.id );

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
          const group_dash = class_dash.children.find(obj => class_id === obj.id);
          if (!group_dash) { // não existe o grupo
            class_dash.children.push({
              level: 3,
              id: group.id,
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
      { id: 0, level: 1, name: 'Dashboard', route: ['/dashboard'], isCollapsed: false },
      { id: 1, level: 1, name: 'Schedule', route: ['/dashboard', 'schedule'], isCollapsed: false },
      { id: 2, level: 1, name: 'Results', route: ['/dashboard', 'results'], isCollapsed: false },
      { id: 3, level: 1, name: 'Classes', route: [], isCollapsed: false , children: []}
    ];
  }

  public navigateRoute(route: string[], collapse_node: any, node_ids: number[]) {

    switch ( node_ids.length ) {
      case 1: {
        this.clearCollapseLevel(1, node_ids[0]);
        break;
      }
      case 2: {
        this.clearCollapseLevel(1, node_ids[0]);
        this.clearCollapseLevel(2, node_ids[1]);
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

    if (route.length > 0 ) {
      this.router.navigate(route);
    }
  }

  public clearCollapseLevel(level: number, noclear: number) {

    switch ( level ) {
      case 1: {
        this.collapse_struture.map( obj => {
          obj.id === noclear ? obj.isCollapsed = true : obj.isCollapsed = false;
        });
        break;
      }
      case 2: {
        this.collapse_struture.map( obj_parent => {
          obj_parent.map( obj => {
            obj.id === noclear ? obj.isCollapsed = true : obj.isCollapsed = false;
          });
        });
        break;
      }
      case 3: {
        this.collapse_struture.map( obj_grand => {
          obj_grand.map( obj_parent => {
            obj_parent.map( obj => {
              obj.id === noclear ? obj.isCollapsed = true : obj.isCollapsed = false;
            });
          });
        });
        break;
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
