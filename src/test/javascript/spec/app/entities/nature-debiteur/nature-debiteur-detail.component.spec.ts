import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { NatureDebiteurDetailComponent } from 'app/entities/nature-debiteur/nature-debiteur-detail.component';
import { NatureDebiteur } from 'app/shared/model/nature-debiteur.model';

describe('Component Tests', () => {
  describe('NatureDebiteur Management Detail Component', () => {
    let comp: NatureDebiteurDetailComponent;
    let fixture: ComponentFixture<NatureDebiteurDetailComponent>;
    const route = ({ data: of({ natureDebiteur: new NatureDebiteur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [NatureDebiteurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NatureDebiteurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NatureDebiteurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load natureDebiteur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.natureDebiteur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
