import { Component, OnInit } from '@angular/core';
import {BreadCrumbService} from '../../../../services/breadcrumb.service';
import {ActivatedRoute} from '@angular/router';
import { Angular2Csv } from 'angular2-csv/Angular2-csv';

@Component({
  selector: 'app-class-score',
  templateUrl: './class-score.component.html',
  styleUrls: ['./class-score.component.css']
})
export class ClassScoreComponent implements OnInit {

  private classId: number;

  constructor(
    private breadCrumbService: BreadCrumbService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.subscribe( params => {
        this.classId = +params['class_id'];
        this.setBreadCrumb();
      }
    );
  }

  public setBreadCrumb(): void {
    // FIXME alterar para a forma dinamica
    this.breadCrumbService.setBreadCrum(['Classes > Arquiteturas Aplicacionais > Results' ]);
  }

  public exportResults(): void {
    // FIXME colocar dados correctos
    const data = [
      {
        name: 'Test 1',
        age: 13,
        average: 8.2,
        approved: true,
        description: 'using \'Content here, content here\' '
      },
      {
        name: 'Test 2',
        age: 11,
        average: 8.2,
        approved: true,
        description: 'using \'Content here, content here\' '
      },
      {
        name: 'Test 4',
        age: 10,
        average: 8.2,
        approved: true,
        description: 'using \'Content here, content here\' '
      },
    ];

    new Angular2Csv(data, 'My Report');
  }
}
