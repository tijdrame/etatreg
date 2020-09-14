import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { Bp3HisDetailComponent } from 'app/entities/bp-3-his/bp-3-his-detail.component';
import { Bp3His } from 'app/shared/model/bp-3-his.model';

describe('Component Tests', () => {
  describe('Bp3His Management Detail Component', () => {
    let comp: Bp3HisDetailComponent;
    let fixture: ComponentFixture<Bp3HisDetailComponent>;
    const route = ({ data: of({ bp3His: new Bp3His(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [Bp3HisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(Bp3HisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(Bp3HisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bp3His on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bp3His).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
