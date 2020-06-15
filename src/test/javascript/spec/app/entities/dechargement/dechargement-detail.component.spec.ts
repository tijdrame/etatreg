import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { DechargementDetailComponent } from 'app/entities/dechargement/dechargement-detail.component';
import { Dechargement } from 'app/shared/model/dechargement.model';

describe('Component Tests', () => {
  describe('Dechargement Management Detail Component', () => {
    let comp: DechargementDetailComponent;
    let fixture: ComponentFixture<DechargementDetailComponent>;
    const route = ({ data: of({ dechargement: new Dechargement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [DechargementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DechargementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DechargementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dechargement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dechargement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
