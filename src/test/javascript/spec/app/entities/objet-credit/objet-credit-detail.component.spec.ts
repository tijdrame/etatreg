import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { ObjetCreditDetailComponent } from 'app/entities/objet-credit/objet-credit-detail.component';
import { ObjetCredit } from 'app/shared/model/objet-credit.model';

describe('Component Tests', () => {
  describe('ObjetCredit Management Detail Component', () => {
    let comp: ObjetCreditDetailComponent;
    let fixture: ComponentFixture<ObjetCreditDetailComponent>;
    const route = ({ data: of({ objetCredit: new ObjetCredit(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [ObjetCreditDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ObjetCreditDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ObjetCreditDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load objetCredit on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.objetCredit).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
