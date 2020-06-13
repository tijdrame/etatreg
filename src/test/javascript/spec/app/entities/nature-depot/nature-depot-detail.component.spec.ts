import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { NatureDepotDetailComponent } from 'app/entities/nature-depot/nature-depot-detail.component';
import { NatureDepot } from 'app/shared/model/nature-depot.model';

describe('Component Tests', () => {
  describe('NatureDepot Management Detail Component', () => {
    let comp: NatureDepotDetailComponent;
    let fixture: ComponentFixture<NatureDepotDetailComponent>;
    const route = ({ data: of({ natureDepot: new NatureDepot(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [NatureDepotDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NatureDepotDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NatureDepotDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load natureDepot on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.natureDepot).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
