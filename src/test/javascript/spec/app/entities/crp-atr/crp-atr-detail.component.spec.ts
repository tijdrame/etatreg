import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { CrpAtrDetailComponent } from 'app/entities/crp-atr/crp-atr-detail.component';
import { CrpAtr } from 'app/shared/model/crp-atr.model';

describe('Component Tests', () => {
  describe('CrpAtr Management Detail Component', () => {
    let comp: CrpAtrDetailComponent;
    let fixture: ComponentFixture<CrpAtrDetailComponent>;
    const route = ({ data: of({ crpAtr: new CrpAtr(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [CrpAtrDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CrpAtrDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CrpAtrDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load crpAtr on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.crpAtr).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
