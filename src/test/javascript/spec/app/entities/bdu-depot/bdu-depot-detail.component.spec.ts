import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BduDepotDetailComponent } from 'app/entities/bdu-depot/bdu-depot-detail.component';
import { BduDepot } from 'app/shared/model/bdu-depot.model';

describe('Component Tests', () => {
  describe('BduDepot Management Detail Component', () => {
    let comp: BduDepotDetailComponent;
    let fixture: ComponentFixture<BduDepotDetailComponent>;
    const route = ({ data: of({ bduDepot: new BduDepot(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BduDepotDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BduDepotDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BduDepotDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bduDepot on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bduDepot).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
