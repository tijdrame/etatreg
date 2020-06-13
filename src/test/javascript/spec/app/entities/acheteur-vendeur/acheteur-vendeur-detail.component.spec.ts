import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { AcheteurVendeurDetailComponent } from 'app/entities/acheteur-vendeur/acheteur-vendeur-detail.component';
import { AcheteurVendeur } from 'app/shared/model/acheteur-vendeur.model';

describe('Component Tests', () => {
  describe('AcheteurVendeur Management Detail Component', () => {
    let comp: AcheteurVendeurDetailComponent;
    let fixture: ComponentFixture<AcheteurVendeurDetailComponent>;
    const route = ({ data: of({ acheteurVendeur: new AcheteurVendeur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [AcheteurVendeurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AcheteurVendeurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AcheteurVendeurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load acheteurVendeur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.acheteurVendeur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
