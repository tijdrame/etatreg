import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BpdeviseDetailComponent } from 'app/entities/bpdevise/bpdevise-detail.component';
import { Bpdevise } from 'app/shared/model/bpdevise.model';

describe('Component Tests', () => {
  describe('Bpdevise Management Detail Component', () => {
    let comp: BpdeviseDetailComponent;
    let fixture: ComponentFixture<BpdeviseDetailComponent>;
    const route = ({ data: of({ bpdevise: new Bpdevise(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BpdeviseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BpdeviseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BpdeviseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bpdevise on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bpdevise).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
