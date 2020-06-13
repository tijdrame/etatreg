import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp4InfosDetailComponent } from 'app/entities/bp-4-infos/bp-4-infos-detail.component';
import { Bp4Infos } from 'app/shared/model/bp-4-infos.model';

describe('Component Tests', () => {
  describe('Bp4Infos Management Detail Component', () => {
    let comp: Bp4InfosDetailComponent;
    let fixture: ComponentFixture<Bp4InfosDetailComponent>;
    const route = ({ data: of({ bp4Infos: new Bp4Infos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp4InfosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(Bp4InfosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Bp4InfosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bp4Infos on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bp4Infos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
