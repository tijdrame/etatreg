import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BpsectinstDetailComponent } from 'app/entities/bpsectinst/bpsectinst-detail.component';
import { Bpsectinst } from 'app/shared/model/bpsectinst.model';

describe('Component Tests', () => {
  describe('Bpsectinst Management Detail Component', () => {
    let comp: BpsectinstDetailComponent;
    let fixture: ComponentFixture<BpsectinstDetailComponent>;
    const route = ({ data: of({ bpsectinst: new Bpsectinst(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BpsectinstDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BpsectinstDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BpsectinstDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bpsectinst on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bpsectinst).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
