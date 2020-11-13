import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BpitrsDetailComponent } from 'app/entities/bpitrs/bpitrs-detail.component';
import { Bpitrs } from 'app/shared/model/bpitrs.model';

describe('Component Tests', () => {
  describe('Bpitrs Management Detail Component', () => {
    let comp: BpitrsDetailComponent;
    let fixture: ComponentFixture<BpitrsDetailComponent>;
    const route = ({ data: of({ bpitrs: new Bpitrs(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BpitrsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BpitrsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BpitrsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bpitrs on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bpitrs).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
