import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BpbqeDetailComponent } from 'app/entities/bpbqe/bpbqe-detail.component';
import { Bpbqe } from 'app/shared/model/bpbqe.model';

describe('Component Tests', () => {
  describe('Bpbqe Management Detail Component', () => {
    let comp: BpbqeDetailComponent;
    let fixture: ComponentFixture<BpbqeDetailComponent>;
    const route = ({ data: of({ bpbqe: new Bpbqe(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BpbqeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BpbqeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BpbqeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bpbqe on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bpbqe).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
