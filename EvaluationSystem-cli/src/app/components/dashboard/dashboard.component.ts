import {AfterViewInit, Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {BreadCrumbService} from '../../services/breadcrumb.service';
import {Router} from '@angular/router';

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
    private breadCrumb: BreadCrumbService
  ) {
  }

  ngOnInit() {
    this.setNamebreadCrum();
    this.createNavbarStructure();
    this.page_navigation_toggled = false;
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

  private createNavbarStructure(): void {
    this.collapse_struture = [
      { name: 'Dashboard', route: ['/dashboard'], isCollapsed: false },
      { name: 'Schedule', route: ['/dashboard', 'schedule'], isCollapsed: false },
      { name: 'Results', route: ['/dashboard', 'results'], isCollapsed: false },
      { name: 'Classes', route: [], isCollapsed: false , children: [
        { name: 'AA', route: ['/dashboard', 'classes', '1'], isCollapsed: false , children: [
          { name: '16/17', route: ['/dashboard', 'classes', '1', 'groups', '1'], isCollapsed: false },
          { name: '15/16', route: ['/dashboard', 'classes', '1', 'groups', '1'], isCollapsed: false },
          { name: '14/15', route: ['/dashboard', 'classes', '1', 'groups', '1'], isCollapsed: false },
        ]},
        { name: 'BB', route: ['/dashboard', 'classes', '1'], isCollapsed: false , children: [
          { name: '16/17', route: ['/dashboard', 'classes', '1', 'groups', '1'], isCollapsed: false },
          { name: '15/16', route: ['/dashboard', 'classes', '1', 'groups', '1'], isCollapsed: false },
          { name: '14/15', route: ['/dashboard', 'classes', '1', 'groups', '1'], isCollapsed: false },
        ]},
        { name: 'CC', route: ['/dashboard', 'classes', '1'], isCollapsed: false },
      ]},
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


}
