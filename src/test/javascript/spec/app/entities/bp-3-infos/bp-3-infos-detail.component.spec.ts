import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp3InfosDetailComponent } from 'app/entities/bp-3-infos/bp-3-infos-detail.component';
import { Bp3Infos } from 'app/shared/model/bp-3-infos.model';

describe('Component Tests', () => {
  describe('Bp3Infos Management Detail Component', () => {
    let comp: Bp3InfosDetailComponent;
    let fixture: ComponentFixture<Bp3InfosDetailComponent>;
    const route = ({ data: of({ bp3Infos: new Bp3Infos(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp3InfosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(Bp3InfosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Bp3InfosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bp3Infos on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bp3Infos).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
