import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp2ComDetailComponent } from 'app/entities/bp-2-com/bp-2-com-detail.component';
import { Bp2Com } from 'app/shared/model/bp-2-com.model';

describe('Component Tests', () => {
  describe('Bp2Com Management Detail Component', () => {
    let comp: Bp2ComDetailComponent;
    let fixture: ComponentFixture<Bp2ComDetailComponent>;
    const route = ({ data: of({ bp2Com: new Bp2Com(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp2ComDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(Bp2ComDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Bp2ComDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bp2Com on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bp2Com).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
