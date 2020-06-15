import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BduAutorDetailComponent } from 'app/entities/bdu-autor/bdu-autor-detail.component';
import { BduAutor } from 'app/shared/model/bdu-autor.model';

describe('Component Tests', () => {
  describe('BduAutor Management Detail Component', () => {
    let comp: BduAutorDetailComponent;
    let fixture: ComponentFixture<BduAutorDetailComponent>;
    const route = ({ data: of({ bduAutor: new BduAutor(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BduAutorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BduAutorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BduAutorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bduAutor on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bduAutor).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
