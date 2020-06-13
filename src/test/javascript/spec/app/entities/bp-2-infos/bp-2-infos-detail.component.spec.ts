import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp2InfosDetailComponent } from 'app/entities/bp-2-infos/bp-2-infos-detail.component';
import { Bp2Infos } from 'app/shared/model/bp-2-infos.model';

describe('Component Tests', () => {
  describe('Bp2Infos Management Detail Component', () => {
    let comp: Bp2InfosDetailComponent;
    let fixture: ComponentFixture<Bp2InfosDetailComponent>;
    const route = ({ data: of({ bp2Infos: new Bp2Infos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp2InfosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(Bp2InfosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Bp2InfosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bp2Infos on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bp2Infos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
