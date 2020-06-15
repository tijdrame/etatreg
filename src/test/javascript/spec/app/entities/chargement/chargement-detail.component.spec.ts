import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { ChargementDetailComponent } from 'app/entities/chargement/chargement-detail.component';
import { Chargement } from 'app/shared/model/chargement.model';

describe('Component Tests', () => {
  describe('Chargement Management Detail Component', () => {
    let comp: ChargementDetailComponent;
    let fixture: ComponentFixture<ChargementDetailComponent>;
    const route = ({ data: of({ chargement: new Chargement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [ChargementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ChargementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ChargementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load chargement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.chargement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
