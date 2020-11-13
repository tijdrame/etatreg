import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BpnaemaDetailComponent } from 'app/entities/bpnaema/bpnaema-detail.component';
import { Bpnaema } from 'app/shared/model/bpnaema.model';

describe('Component Tests', () => {
  describe('Bpnaema Management Detail Component', () => {
    let comp: BpnaemaDetailComponent;
    let fixture: ComponentFixture<BpnaemaDetailComponent>;
    const route = ({ data: of({ bpnaema: new Bpnaema(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BpnaemaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BpnaemaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BpnaemaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bpnaema on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bpnaema).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
