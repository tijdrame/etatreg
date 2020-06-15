import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BduEffetDetailComponent } from 'app/entities/bdu-effet/bdu-effet-detail.component';
import { BduEffet } from 'app/shared/model/bdu-effet.model';

describe('Component Tests', () => {
  describe('BduEffet Management Detail Component', () => {
    let comp: BduEffetDetailComponent;
    let fixture: ComponentFixture<BduEffetDetailComponent>;
    const route = ({ data: of({ bduEffet: new BduEffet(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BduEffetDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BduEffetDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BduEffetDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bduEffet on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bduEffet).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
