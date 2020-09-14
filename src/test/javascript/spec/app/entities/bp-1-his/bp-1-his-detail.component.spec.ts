import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp1HisDetailComponent } from 'app/entities/bp-1-his/bp-1-his-detail.component';
import { Bp1His } from 'app/shared/model/bp-1-his.model';

describe('Component Tests', () => {
  describe('Bp1His Management Detail Component', () => {
    let comp: Bp1HisDetailComponent;
    let fixture: ComponentFixture<Bp1HisDetailComponent>;
    const route = ({ data: of({ bp1His: new Bp1His(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp1HisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(Bp1HisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Bp1HisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bp1His on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bp1His).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
