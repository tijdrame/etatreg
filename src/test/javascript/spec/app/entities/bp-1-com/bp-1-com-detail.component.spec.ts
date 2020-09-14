import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp1ComDetailComponent } from 'app/entities/bp-1-com/bp-1-com-detail.component';
import { Bp1Com } from 'app/shared/model/bp-1-com.model';

describe('Component Tests', () => {
  describe('Bp1Com Management Detail Component', () => {
    let comp: Bp1ComDetailComponent;
    let fixture: ComponentFixture<Bp1ComDetailComponent>;
    const route = ({ data: of({ bp1Com: new Bp1Com(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp1ComDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(Bp1ComDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Bp1ComDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bp1Com on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bp1Com).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
